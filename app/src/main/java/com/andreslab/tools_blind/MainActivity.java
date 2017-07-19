package com.andreslab.tools_blind;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.actions.utilities.UT_calculator;
import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;
import com.andreslab.tools_blind.commands.CommandList;
import com.andreslab.tools_blind.commands.ControllerCommands;
import com.andreslab.tools_blind.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{


    private static final String DEBUG_TAG_GESTURE = "Gesture";
    private GestureDetectorCompat mDetector;
    ControllerCommands cc = new ControllerCommands(MainActivity.this);
    VoiceToSpeech vts;
    Vibrator vibrator;
    Boolean validateOnLongPress;

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
                        //parametros del comando local activo
                        vts.voiceToSpeech("save Parameters success of local command "+ControllerCommands.LastLocalCommand);
                        ControllerCommands.isListeningArgument = false;
                        ControllerCommands.SuccessInputParameter = true;
                        validateOnLongPress = false;

                        //save parameters
                        for (int i = 0; i <= ControllerCommands.parametersLocalCommand.size() - 1; i++) {
                            ControllerCommands.parameters.put( ControllerCommands.LastLocalCommand, ControllerCommands.parametersLocalCommand.get(i).toString());
                        }
                    }else{
                        //nuevo comando
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

