package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;

/**
 * Created by macbookpro on 7/17/17.
 */

public class GlobalActions {

    Context context;
    Activity activity;

    //voice command
    static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    
    public GlobalActions(Context ct, Activity act){
        this.context = ct;
        this.activity = act;
    }

    public void voice_command(){
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try{
            this.activity.startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        }catch(ActivityNotFoundException e){
            Toast.makeText(context, "El dispositivo no soporta reconocimiento de voz", Toast.LENGTH_SHORT).show();
        }
    }

}
