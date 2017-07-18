package com.andreslab.tools_blind;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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

    static final int  MY_PERMISSION_REQUEST =1;
    //static final int MY_PERMISSION_REQUEST_CALL_PHONE =1;
    ArrayList<PermissionModel> name_permissions = new ArrayList<PermissionModel>();
    String[] permission_name = {"camera","call_phone", "write_external_storage", "read_contacts"};
    String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        init_permissions();
    }


    public void init_permissions(){
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        ArrayList<PermissionModel> pm = new ArrayList<PermissionModel>();
        pm =   rdb.showData_permissions();


        //Log.d("tag", pm.get(0).getPermission_name());

        if(pm.size() < 1){
            Log.d("DATA_BASE", "no existe base de datos");
            checkPermission();
        }else{
            Log.d("DATA_BASE", "existe base de datos");
            Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
            startActivity(i);
        }


    }

    public void post_request_permissions(){
        //save in database
        RequestDataBase rdb = new RequestDataBase(PermissionsActivity.this, PermissionsActivity.this);
        rdb.saveData_permissions(name_permissions);
        Intent i = new Intent(PermissionsActivity.this, MainActivity.class);
        startActivity(i);

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void checkPermission(){


        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSION_REQUEST);
        }

    }



    /*public void checkPermissionCallPhone(){
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //SI NO POSEE PERMISOS
            if(ActivityCompat.shouldShowRequestPermissionRationale(PermissionsActivity.this, Manifest.permission.CALL_PHONE)){

            }else{
                ActivityCompat.requestPermissions(PermissionsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST);
            }
        }else{
            Log.d("PERMISSIONS", "Existen permisos para la llamar");
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  MY_PERMISSION_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 1));
                    Toast.makeText(this, "Se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(0,permission_name[0], 0));
                    Toast.makeText(this, "No se otorgó permisos a la cámara", Toast.LENGTH_SHORT).show();
                }


                //callphone
                if(grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(1,permission_name[1], 1));
                    Toast.makeText(this, "Se otorgó permisos para llamar", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(1,permission_name[1], 0));
                    Toast.makeText(this, "No se otorgó permisos para llamar", Toast.LENGTH_SHORT).show();
                }

                //wrrite external storage
                if(grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(2,permission_name[2], 1));
                    Toast.makeText(this, "Se otorgó permisos para almacenamiento", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(2,permission_name[2], 0));
                    Toast.makeText(this, "No se otorgó permisos para almacenamiento", Toast.LENGTH_SHORT).show();
                }

                //read contacts
                if(grantResults.length > 0 && grantResults[3] == PackageManager.PERMISSION_GRANTED){
                    this.name_permissions.add(new PermissionModel(3,permission_name[3], 1));
                    Toast.makeText(this, "Se otorgó permisos para revisar los contactos", Toast.LENGTH_SHORT).show();
                }else{
                    this.name_permissions.add(new PermissionModel(3,permission_name[3], 0));
                    Toast.makeText(this, "No se otorgó permisos para revisar los contactos", Toast.LENGTH_SHORT).show();
                }

        }
        //Log.d("TAG", "EJECUCIÓN FINAL");
        for(int i = 0; i <= name_permissions.size()-1; i++){
            Log.i("PERMISOS", "id: "+ name_permissions.get(i).getId() + " - permiso: "+name_permissions.get(i).getPermission_name()+ " - acceso: "+name_permissions.get(i).getAccess());
        }
        post_request_permissions();

    }
}





