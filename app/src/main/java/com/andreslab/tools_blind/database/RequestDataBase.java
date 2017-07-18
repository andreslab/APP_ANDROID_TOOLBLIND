package com.andreslab.tools_blind.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
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


    public void saveData(ArrayList<PermissionModel> lista_permisos){
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_permisos", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        try{
            for (int i = 0; i == lista_permisos.size(); i++ ) {
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
        }
    }
    public void updateData(){}
    public void showData(){}
}
