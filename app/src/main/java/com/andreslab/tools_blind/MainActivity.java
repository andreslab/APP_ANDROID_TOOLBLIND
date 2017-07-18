package com.andreslab.tools_blind;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.andreslab.tools_blind.view.MainView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{


    private static final String DEBUG_TAG_GESTURE = "Gesture";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(MainActivity.this));

        //GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);
        //GestureDetector.OnDoubleTapListener
        mDetector.setOnDoubleTapListener(this);


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
        Log.d(DEBUG_TAG_GESTURE, "on doubletap");
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

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

        return true;
    }



}
