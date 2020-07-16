package com.andreslab.tools_blind;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.view.DigitalBrailleView;
import com.andreslab.tools_blind.view.MainView;

public class DigitalBraille extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new DigitalBrailleView(DigitalBraille.this, DigitalBraille.this));
    }
}
