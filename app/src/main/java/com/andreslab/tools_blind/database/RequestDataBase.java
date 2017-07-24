package com.andreslab.tools_blind.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.models.PermissionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 7/17/17.
 */

public class RequestDataBase {

    Context context;
    Activity activity;

    public RequestDataBase(Context ct, Activity act){
        context = ct;
        activity = act;
    }


    public void saveData_permissions(ArrayList<PermissionModel> lista_permisos){
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_permisos", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        try{
            for (int i = 0; i <= lista_permisos.size()-1; i++ ) {
                ContentValues cv = new ContentValues();
                cv.put("id", lista_permisos.get(i).getId());
                cv.put("permiso", lista_permisos.get(i).getPermission_name());
                cv.put("acceso", lista_permisos.get(i).getAccess());
                db.insert("permisos", null, cv);

            }
            db.close();
            Toast.makeText(context, "Lista de permisos guardada en la base de datos", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Se presentó el problema "+e.toString()+" al guardar los datos ", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    public void updateData_permissions(){}


    public ArrayList<PermissionModel> showData_permissions(){
        ArrayList<PermissionModel> lista_permisos = new ArrayList<PermissionModel>();
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_permisos", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        String columnas[] = {"id", "permiso", "acceso"};

        Cursor cursor = db.query("permisos",columnas,null,null,null,null,"id");

        int id = cursor.getColumnIndex(columnas[0]);
        int permiso = cursor.getColumnIndex(columnas[1]);
        int acceso = cursor.getColumnIndex(columnas[2]);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            lista_permisos.add(new PermissionModel(cursor.getInt(id), cursor.getString(permiso), cursor.getInt(acceso)));
        }
        db.close();
        return lista_permisos;
    }



    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    public void saveData_config_email(ArrayList<PermissionModel> lista_permisos){}
    public void updateData_config_email(){}
    //public ArrayList<PermissionModel> showData_config_email(){}
}
