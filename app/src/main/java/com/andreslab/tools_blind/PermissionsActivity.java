package com.andreslab.tools_blind;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PermissionsActivity extends AppCompatActivity {

    static final int MY_PERMISSION_REQUEST_IMAGE_CAPTURE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
    }


    public Boolean checkPermissionCamera(){
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //SI NO POSEE PERMISOS
            if(ActivityCompat.shouldShowRequestPermissionRationale(PermissionsActivity.this, Manifest.permission.CAMERA)){

            }else{
                ActivityCompat.requestPermissions(PermissionsActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_IMAGE_CAPTURE);
            }

            return false;
        }else{
            Log.d("PERMISSIONS", "Existen permisos para la cámara");
            return true;
        }
    }

}





