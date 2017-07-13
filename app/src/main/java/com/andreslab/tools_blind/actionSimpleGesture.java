package com.andreslab.tools_blind;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by macbookpro on 7/13/17.
 */

public class actionSimpleGesture {

    private Context context;
    private Activity activity;

    public actionSimpleGesture(Context c, Activity ac){
        this.context = c;
        this.activity = ac;
    }

    //camera
    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int MY_PERMISSION_REQUEST_IMAGE_CAPTURE =1;

    //left <--
    public void back_view(){}

    //right -->
    public void new_record(){}

    //botton
    public void take_camera(PackageManager pm){
        Boolean permission = checkPermissionCamera();

        if (permission) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(pm) != null){
                this.activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }


    }

    //up
    public void check_notes(){}

    //single tap
    public void accept(){}



    //permission
    public Boolean checkPermissionCamera(){
        if(ContextCompat.checkSelfPermission(this.context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            //entra si no posee permiso
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
                //explicación del permiso

            }else{
                //realizamos la peticion
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSION_REQUEST_IMAGE_CAPTURE);

            }

            return false;

        }else{

            Log.d("TAG","existen permisos para la camara");
            return true;
        }
    }

}
