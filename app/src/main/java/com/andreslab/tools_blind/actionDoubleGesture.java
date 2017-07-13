package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
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
    public void digital_braille(){}

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
