package com.andreslab.tools_blind;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.actions.utilities.UT_sendEmail;
import com.andreslab.tools_blind.commands.ControllerCommands;
import com.andreslab.tools_blind.view.MainView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{


    //::send email
    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    //::::::::::::


    private static final int ALARM_REQUEST_CODE = 1;

    private static final String DEBUG_TAG_GESTURE = "Gesture";
    private GestureDetectorCompat mDetector;
    ControllerCommands cc = new ControllerCommands(MainActivity.this);
    VoiceToSpeech vts;
    Vibrator vibrator;
    Boolean validateOnLongPress;
    Hashtable<String,String> parametros = new Hashtable<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(MainActivity.this));

        //GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);
        //GestureDetector.OnDoubleTapListener
        mDetector.setOnDoubleTapListener(this);
        vts = new VoiceToSpeech(MainActivity.this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        validateOnLongPress = false;


        //parametros.put("mensaje", "hola");


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        int action = event.getActionMasked();

        switch (action){
                case (MotionEvent.ACTION_DOWN):

                    break;
                case (MotionEvent.ACTION_MOVE):

                    break;
                case (MotionEvent.ACTION_UP):

                    break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {



        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        GlobalActions ga = new GlobalActions(MainActivity.this, MainActivity.this);
        ga.voice_command();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {

        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {


        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {


        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        validateOnLongPress = true;
        vibrator.vibrate(200000);
        if(ControllerCommands.isListeningArgument){
            //vts.voiceToSpeech("Local command listening is "+ControllerCommands.LastLocalCommand);
            //se agrega el valor del comando local
            GlobalActions ga = new GlobalActions(MainActivity.this, MainActivity.this);
            ga.voice_command();
            vibrator.cancel();
        }else {
            Log.d("ESCUCHANDO ARGUMENTO", "no existe un subcomando que esté activo");
            vts.voiceToSpeech("Sorry, not found local command listening");
            validateOnLongPress = false;
            String listParameters = "";
            for (int i = 0; i <= ControllerCommands.parametersLocalCommand.size() - 1; i++) {
                listParameters += "\n" + ControllerCommands.parametersLocalCommand.get(i).toString() + "\n";
            }
            vibrator.cancel();
        }

        }


    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GlobalActions.RECOGNIZE_SPEECH_ACTIVITY:
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech = speech.get(0);
                    Log.d("DATA SPEECH", strSpeech);
                    if(ControllerCommands.isListeningArgument && ControllerCommands.GlobalCommandSelected && validateOnLongPress && strSpeech.length() > 1){
                        Log.d("OPCIONES",".....op 1 .......");
                        //parametros del comando local activo
                        //vts.voiceToSpeech("save Parameters success of local command "+ControllerCommands.LastLocalCommand);
                        parametros.put(ControllerCommands.LastLocalCommand,strSpeech);
                        Log.d("PARAMETRO GUARDADO", parametros.toString());
                        ControllerCommands.isListeningArgument = false;
                        ControllerCommands.SuccessInputParameter = true;
                        validateOnLongPress = false;

                        //save parameters
                        for (int i = 0; i <= ControllerCommands.parametersLocalCommand.size() - 1; i++) {
                            ControllerCommands.parameters.put( ControllerCommands.LastLocalCommand, ControllerCommands.parametersLocalCommand.get(i).toString());
                        }

                    } else if(strSpeech.equals("ejecutar") && !parametros.isEmpty()){
                        Log.d("OPCIONES",".....op 2 .......");
                        executeCommand();
                    }
                    else {
                        Log.d("OPCIONES",".....op 1 .......");
                        //nuevo comando tanto global como local
                        String commandType =  this.cc.executeAndAddCommand(strSpeech);
                        executeCommandFunction(commandType);
                    }




                }
                break;
        }
    }

    private void executeCommandFunction(String ct) {

        Log.d("LIST COMMANDS", "tamaño: " + ControllerCommands.listCommands.size());

        switch (ct) {
            case "GLOBAL":
                Log.d("EJECUCIÓN", "tipo de comando global");

                switch (ControllerCommands.GlobalCommands) {
                    case "nueva foto":
                        Intent f = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(f);
                        break;
                    case "nueva práctica":
                        Intent d = new Intent(MainActivity.this, DigitalBraille.class);
                        startActivity(d);
                        break;

                    default:
                }

                break;
            case "LOCAL":
                Log.d("EJECUCIÓN", "tipo de comando local");

                if (ControllerCommands.GlobalCommands.equals("nueva operación")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva llamada")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "contacto":
                            VoiceToSpeech vs = new VoiceToSpeech(MainActivity.this);
                            vs.voiceToSpeech("contact new");
                            break;

                        default:
                    }
                }
                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva traducción")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "inglés a español":

                            break;
                        case "español a inglés":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva alarma")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "mensaje":

                            break;
                        case "dia":

                            break;
                        case "hora":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva email")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "emisor":


                            break;
                        case "receptor":

                            break;
                        case "asunto":

                            break;
                        case "mensaje":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva nota")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "tema":

                            break;
                        case "grabar":

                            break;

                        default:
                    }
                }
                //::::::::::::::::: en desarrollo ::::::::::::::::::
                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nuevo diagrama")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear titulo":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva práctica")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nuevo documento")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva presentación")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nuevaojuego")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::
                if (ControllerCommands.GlobalCommands.equals("nueva ubicación")) {
                    switch (ControllerCommands.LastLocalCommand) {

                        case "crear operacion":

                            break;

                        default:
                    }
                }

                //::::::::::::::::::::::::::::::::::::::::::::::::::

        }




        }


        private void executeCommand(){

            if (ControllerCommands.GlobalCommands.equals("nueva operación")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva llamada")) {
                Log.d("NUEVA LLAMADA", "execute");
                if(parametros.containsKey("contacto") || parametros.containsKey("teléfono")) {

                    //extraer teléfono de un contacto


                    //usar teléfono directo
                    String tel = parametros.get("teléfono");
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                            PackageManager.PERMISSION_GRANTED) {
                        Intent e = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                        startActivity(e);
                    }
                }
            }
            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva traducción")) {

            }


            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva alarma")) {
                Log.d("NUEVA ALARMA", "execute");

                AlarmManager alarmMgr;
                PendingIntent alarmIntent;

                alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                alarmIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Calendar calendar = Calendar.getInstance();

                //definir tiempo
                String t = parametros.get("hora");
                String hora = "";
                String min = "";
                Boolean activeY = false;
                ArrayList<String> rango_num = new ArrayList<String>();
                int position = 0;

                if(t.contains("y") || t.contains(":")){
                    for(Character e:t.toCharArray()){
                        if(e.toString().equals("y") || e.toString().equals(":")){
                            activeY = true;
                        }else if (activeY){
                            if(!e.toString().equals(" ")){
                                min += e.toString();
                            }
                        }else{
                            if(!e.toString().equals(" ")) {
                                hora += e.toString();
                            }
                        }
                    }
                    //definir hora

                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(min));


                }else if(t.contains("en")){
                    if (t.contains("segundos") || t.contains("segundo")){
                        for(Character e:t.toCharArray()){
                            if(e.toString().equals(" ")){
                                rango_num.add(String.valueOf(position));

                            }
                            position++;
                        }
                        position = 0;

                        int inicio = Integer.parseInt(rango_num.get(0));
                        int fin = Integer.parseInt(rango_num.get(1));
                        Log.d("NUEVA ALARMA","inicio:"+inicio+" fin:"+fin);
                        String v = t.substring(inicio, fin);
                        try {
                            int valor = Integer.parseInt(v);
                            calendar.setTimeInMillis(System.currentTimeMillis() + valor * 1000);
                        }catch (Exception e){
                            Log.d("NUEVA ALARMA","el valor ingresado no es correcto");
                        }


                    }else if(t. contains("minuto") || t.contains("minutos")){
                        for(Character e:t.toCharArray()){
                            if(e.toString().equals(" ")){
                                rango_num.add(String.valueOf(position));

                            }
                            position++;
                        }
                        position = 0;

                        int inicio = Integer.parseInt(rango_num.get(0));
                        int fin = Integer.parseInt(rango_num.get(1));
                        Log.d("NUEVA ALARMA","inicio:"+inicio+" fin:"+fin);
                        String v = t.substring(inicio, fin);
                        try {
                            int valor = Integer.parseInt(v);
                            //1000 por milisegundos, 60 por cada segundo tiene 0 segundos
                            calendar.setTimeInMillis(System.currentTimeMillis() + valor * 1000 * 60);
                        }catch (Exception e){
                            Log.d("NUEVA ALARMA","el valor ingresado no es correcto");
                        }
                    }
                }


                Log.d("NUEVA ALARMA", "HORA DEFINIDA: "+calendar.getTimeInMillis());
                Log.d("NUEVA ALARMA", "HORA ACTUAL: "+System.currentTimeMillis());

                // Repeticiones en intervalos de 20 minutos
                /*alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60 * 20, alarmIntent);*/

                alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                calendar.clear();

            }
            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nuevo email")) {
                String emisor = "";
                String receptor = "";
                String asunto = "sin título";
                String mensaje = "";

                parametros.put("emisor","jaimeandrade454@gmail.com");
                parametros.put("receptor","andres_j-a@hotmail.com");

                if (parametros.containsKey("emisor") &&
                        parametros.containsKey("receptor") &&
                        parametros.containsKey("mensaje")) {

                    //emisor = parametros.get("emisor");//optener mail por nombre en a base de datos
                    emisor = "jaimeandrade454@gmail.com";
                    receptor = parametros.get("receptor");
                    if(parametros.containsKey("asunto")){
                        asunto = parametros.get("asunto");
                    }
                    mensaje = parametros.get("mensaje");

                    UT_sendEmail sm = new UT_sendEmail(MainActivity.this, receptor, asunto, mensaje);
                    //Executing sendmail to send email
                    sm.execute();

                    Log.d("NUEVO EMAIL","mensaje enviado");
                }
            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva nota")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nuevo diagrama")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva práctica")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nuevo documento")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva presentación")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nuevaojuego")) {

            }

            //::::::::::::::::::::::::::::::::::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("nueva ubicación")) {

            }




            //::::::::::::::::::::::configuración::::::::::::::::::
            if (ControllerCommands.GlobalCommands.equals("configurar email")) {

            }
        }



    }

