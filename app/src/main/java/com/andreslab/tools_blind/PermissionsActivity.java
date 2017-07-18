package com.andreslab.tools_blind;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.database.RequestDataBase;
import com.andreslab.tools_blind.models.PermissionModel;

import java.util.ArrayList;
import java.util.List;

public class PermissionsActivity extends AppCompatActivity {

    static final int MY_PERMISSION_REQUEST_IMAGE_CAPTURE =1;
    ArrayList<PermissionModel> name_permissions = new ArrayList<PermissionModel>();
    String[] permission_name = {"camera"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init_permissions();
    }


    public void init_permissions(){
        checkPermissionCamera();
    }

    public void post_request_permissions(){
        //save in database
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        rdb.saveData(name_permissions);
    }


    public void checkPermissionCamera(){
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //SI NO POSEE PERMISOS
            if(ActivityCompat.shouldShowRequestPermissionRationale(PermissionsActivity.this, Manifest.permission.CAMERA)){

            }else{
                ActivityCompat.requestPermissions(PermissionsActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST_IMAGE_CAPTURE);
            }
        }else{
            Log.d("PERMISSIONS", "Existen permisos para la cámara");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST_IMAGE_CAPTURE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 1));
                    Toast.makeText(this, "Se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 0));
                    Toast.makeText(this, "No se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }
        }
        //Log.d("TAG", "EJECUCIÓN FINAL");
        for(int i = 0; i <= name_permissions.size()-1; i++){
            Log.i("PERMISOS", "id: "+ name_permissions.get(i).getId() + "-permiso: "+name_permissions.get(i).getPermission_name()+ "-acceso: "+name_permissions.get(i).getAccess());
        }
        post_request_permissions();

    }
}





