package com.andreslab.tools_blind.actions;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by macbookpro on 7/18/17.
 */

public class VoiceToSpeech {
    private Context context;
    private TextToSpeech ttobj;

    public VoiceToSpeech(Context c){
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
