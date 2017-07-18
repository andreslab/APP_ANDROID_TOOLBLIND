package com.andreslab.tools_blind;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.andreslab.tools_blind.actions.utilities.UT_calculator;
import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;
import com.andreslab.tools_blind.commands.ControllerCommands;
import com.andreslab.tools_blind.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{


    private static final String DEBUG_TAG_GESTURE = "Gesture";
    private GestureDetectorCompat mDetector;
    ControllerCommands cc = new ControllerCommands(MainActivity.this);

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
        if(ControllerCommands.isListeningArgument){

        }else{
            Log.d("ESCUCHANDO ARGUMENTO", "no existe un subcomando que esté activo");
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

                   String commandType =  this.cc.executeAndAddCommand(strSpeech);
                    executeCommandFunction(commandType);

                }
                break;
        }
    }

    private void executeCommandFunction(String ct){

        Log.d("LIST COMMANDS", "tamaño: "+ ControllerCommands.listCommands.size());

        switch (ct){
            case "GLOBAL":
                Log.d("EJECUCIÓN","tipo de comando global");

                switch (ControllerCommands.GlobalCommands){
                    case "nueva foto":
                        Intent i = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(i);
                        break;

                    default:
                }

                break;
            case "LOCAL":
                Log.d("EJECUCIÓN","tipo de comando local");
                if(ControllerCommands.GlobalCommands == "calculadora"){
                    switch (ControllerCommands.LastLocalCommand){
                        case "crear operacion":

                            break;

                         default:
                    }
                }

                break;
            default:
                Log.d("EJECUCIÓN"," ninguno");
        }

    }
}
