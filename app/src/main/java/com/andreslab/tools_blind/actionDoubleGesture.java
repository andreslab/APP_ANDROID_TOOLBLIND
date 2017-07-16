package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by macbookpro on 7/13/17.
 */

public class actionDoubleGesture {

    private Context context;
    private Activity activity;

    public actionDoubleGesture(Context c, Activity ac){
        this.context = c;
        this.activity = ac;
    }

    //voice command
    static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    //double left <--
    public void record_tracking(){}

    //double right -->
    public void digital_braille(Button btn1){
       /* Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        long[] patron = {0,500,300,1000,500}; //invertido se detiene por cierto y tiempo y luego vibra por cierto tiempo
        v.vibrate(patron, 3); // se repetirá 3 veces*/

        btn1.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
                long[] patron = {0,500,100,500,100}; //invertido se detiene por cierto y tiempo y luego vibra por cierto tiempo
                v.vibrate(patron, 1);
                return true;
            }
        });

    }

    //double botton
    public void new_slides(){}

    //double up
    public void mental_exercises(){}

    //double tap
    public void voice_command(){
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        //configurar el lenguaje Español - Mexico
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try{
            this.activity.startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this.context, "El dispositivo no soporta reconocimiento de voz", Toast.LENGTH_SHORT).show();
        }
    }


}
