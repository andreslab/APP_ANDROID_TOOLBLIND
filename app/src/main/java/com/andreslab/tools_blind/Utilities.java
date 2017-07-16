package com.andreslab.tools_blind;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by macbookpro on 7/16/17.
 */

public class Utilities {

    private Context context;
    private TextToSpeech ttobj;

    public Utilities(Context c){
        this.context = c;
    }


    public void voiceToSpeech(final String textInput){

        ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int i) {
                ttobj.setLanguage((Locale.US));
                ttobj.speak(textInput, TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }
}
