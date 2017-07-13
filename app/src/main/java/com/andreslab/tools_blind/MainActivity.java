package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{


    private static final String DEBUG_TAG_GESTURE = "Gesture";
    private GestureDetectorCompat mDetector;

    //btn
    Button btn_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);
        //GestureDetector.OnDoubleTapListener
        mDetector.setOnDoubleTapListener(this);

        btn_action = (Button) findViewById(R.id.btn_action);
        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //contexto es MainActivity.class
                //actionSimpleGesture asg = new actionSimpleGesture(MainActivity.this, MainActivity.this);
                //asg.take_camera(getPackageManager());

                actionDoubleGesture adg = new actionDoubleGesture(MainActivity.this, MainActivity.this);
                adg.voice_command();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);

        int action = event.getActionMasked();
        int index = event.getActionIndex();
        int finger = event.getPointerCount();
        int pointerId = event.getPointerId(index);
       // int position = event.getPointerCoords();
        float posX = event.getX();
        float posY = event.getY();



        switch (action){
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG_GESTURE, "Action down - gesture has started");
                Log.d(DEBUG_TAG_GESTURE, "fingers: "+ finger);
                Log.d(DEBUG_TAG_GESTURE, "Coord x: "+ posX);
                Log.d(DEBUG_TAG_GESTURE, "Coord y: "+ posY);

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {


        //Log.d(DEBUG_TAG_GESTURE,"onSingleTapConfirmed"+motionEvent.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onDoubleTap"+motionEvent.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onDoubleTapEvent"+motionEvent.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onDown"+motionEvent.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onShowPress"+motionEvent.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onSingleTapUp"+motionEvent.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(DEBUG_TAG_GESTURE,"onScroll---inicio: "+motionEvent.getX());
        Log.d(DEBUG_TAG_GESTURE,"onScroll---fin:   "+motionEvent1.getX());

        Log.d(DEBUG_TAG_GESTURE,"onScroll---inicio---total: "+motionEvent.toString());
        Log.d(DEBUG_TAG_GESTURE,"onScroll---fin---total: "+motionEvent1.toString());

        Log.d(DEBUG_TAG_GESTURE,"onScroll---number finger 1: "+motionEvent.getPointerCount());
        Log.d(DEBUG_TAG_GESTURE,"onScroll---number finger 2: "+motionEvent1.getPointerCount());
        //Log.d(DEBUG_TAG_GESTURE,"onScroll x"+ v);
        //Log.d(DEBUG_TAG_GESTURE,"onScroll y"+ v1);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //Log.d(DEBUG_TAG_GESTURE,"onLongPress"+motionEvent.toString());

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(DEBUG_TAG_GESTURE,"onFling"+motionEvent.toString());
        Log.d(DEBUG_TAG_GESTURE,"onFling x inicio: "+ motionEvent.getX());
        Log.d(DEBUG_TAG_GESTURE,"onFling x fin: "+ motionEvent1.getX());
        Log.d(DEBUG_TAG_GESTURE,"onFling y inicio: "+ motionEvent.getY());
        Log.d(DEBUG_TAG_GESTURE,"onFling y fin: "+ motionEvent1.getY());

        Log.d(DEBUG_TAG_GESTURE,"onFling finger - inicio poinerCount: "+ motionEvent.getPointerCount());
        Log.d(DEBUG_TAG_GESTURE,"onFling finger - fin poinerCount: "+ motionEvent1.getPointerCount());

        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case actionSimpleGesture.MY_PERMISSION_REQUEST_IMAGE_CAPTURE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //grantresult si el tamaño es mayor a 0 implica que ya esta por lo menos un permiso

                }else{
                    Toast.makeText(this,"No hay permisos de cámara", Toast.LENGTH_SHORT).show();
                }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case actionDoubleGesture.RECOGNIZE_SPEECH_ACTIVITY:
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech = speech.get(0);
                    Log.d("TAG-SPEECH",strSpeech);
                }else{

                }

        }
    }
}
