package com.andreslab.tools_blind;

import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmActivity extends android.content.BroadcastReceiver {

    int numberOfReplay = 5;
    int currentReplay = 0;

    @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
        Toast.makeText(context, "Alarma....", Toast.LENGTH_LONG).show();

        MediaPlayer mp = MediaPlayer.create(context, R.raw.alarma);
        mp.start();
        currentReplay++;
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if( currentReplay < numberOfReplay){
                    mp.start();
                    currentReplay++;
                }

            }
        });

    }
}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }*/


