package com.andreslab.tools_blind.actions.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.andreslab.tools_blind.CameraActivity;
import com.andreslab.tools_blind.actions.VoiceToSpeech;
import com.andreslab.tools_blind.commands.ControllerCommands;

/**
 * Created by macbookpro on 7/18/17.
 */

public class UT_newPhoto {

    Activity activity;
    PackageManager packageManager;
    public static final int REQUEST_IMAGE_CAPTURE = 1;


    public UT_newPhoto(Activity ac, PackageManager pm){
        activity = ac;
        packageManager = pm;


    }

    public void takePicture(){

        Boolean permission = true;
      if(permission){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(packageManager) != null){
                activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }else{
            Log.d("PERMISSIONS","no posee permisos de cámara");

        }


    }
}
