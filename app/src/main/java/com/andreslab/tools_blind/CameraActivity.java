package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andreslab.tools_blind.actions.utilities.UT_newPhoto;

public class CameraActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        UT_newPhoto np = new UT_newPhoto(CameraActivity.this, getPackageManager());
        np.takePicture();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UT_newPhoto.REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK && null != data) {
                    Log.d("CAMERA ACTIVITY", "ejecutando camara");
                    finish();
                }
                break;
        }
        //finish();
        }
    }

