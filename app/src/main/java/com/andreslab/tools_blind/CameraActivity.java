package com.andreslab.tools_blind;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andreslab.tools_blind.actions.saveImageACT;
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
                    //convertir imagen

                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");


                    //guardar imagen
                    saveImageACT savefile = new saveImageACT();
                    savefile.SaveImage(CameraActivity.this, imageBitmap);

                    Log.d("CAMERA ACTIVITY", "ejecutando camara");
                    finish();
                }
                break;
        }
        //finish();
        }
    }

