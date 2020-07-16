package com.andreslab.tools_blind.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.andreslab.tools_blind.models.AccountModel;
import com.andreslab.tools_blind.models.ContactsModel;
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

    public void saveData_contacts(ArrayList<ContactsModel> lista_contactos){
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_contactos", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        try{
            for (int i = 0; i <= lista_contactos.size()-1; i++ ) {
                ContentValues cv = new ContentValues();
                cv.put("id", lista_contactos.get(i).getId());
                cv.put("nombre", lista_contactos.get(i).getName());
                cv.put("telefono", lista_contactos.get(i).getPhone());
                cv.put("correo", lista_contactos.get(i).getEmail());

                db.insert("contactos", null, cv);

            }
            db.close();
            Toast.makeText(context, "Contactos guardados en la base de datos", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Se presentó el problema "+e.toString()+" al guardar los datos ", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    //public void updateData_contact(){}
    public ArrayList<ContactsModel> showData_contacts(){
        ArrayList<ContactsModel> lista_contactos = new ArrayList<ContactsModel>();
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_contactos", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        String columnas[] = {"id", "nombre", "telefono","correo"};

        Cursor cursor = db.query("contactos",columnas,null,null,null,null,"id");

        int id = cursor.getColumnIndex(columnas[0]);
        int nombre = cursor.getColumnIndex(columnas[1]);
        int telefono = cursor.getColumnIndex(columnas[2]);
        int correo = cursor.getColumnIndex(columnas[3]);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            lista_contactos.add(new ContactsModel(cursor.getInt(id), cursor.getString(nombre), cursor.getString(telefono), cursor.getString(correo)));
        }
        db.close();
        return lista_contactos;
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    public void saveData_account(ArrayList<AccountModel> cuenta){
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_cuenta", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        try{
            /*for (int i = 0; i <= lista_permisos.size()-1; i++ ) {
                ContentValues cv = new ContentValues();
                cv.put("id", lista_permisos.get(i).getId());
                cv.put("permiso", lista_permisos.get(i).getPermission_name());
                cv.put("acceso", lista_permisos.get(i).getAccess());
                db.insert("permisos", null, cv);
            }*/
            ContentValues cv = new ContentValues();
            cv.put("id", cuenta.get(0).getId());
            cv.put("email", cuenta.get(0).getEmail());
            cv.put("pass", cuenta.get(0).getPass());
            db.insert("cuenta", null, cv);


            db.close();
            Toast.makeText(context, "Cuenta guardada en la base de datos", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Se presentó el problema "+e.toString()+" al guarda la cuenta ", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    public void updateData_account(){}


    public ArrayList<AccountModel> showData_account(){
        ArrayList<AccountModel> cuenta = new ArrayList<AccountModel>();
        AdminSQLiteHelper adminSQLiteHelper = new AdminSQLiteHelper(context, "admin_cuenta", null , 1);
        SQLiteDatabase db = adminSQLiteHelper.getWritableDatabase();
        String columnas[] = {"id", "email", "pass"};

        Cursor cursor = db.query("cuenta",columnas,null,null,null,null,"id");

        int id = cursor.getColumnIndex(columnas[0]);
        int email = cursor.getColumnIndex(columnas[1]);
        int pass = cursor.getColumnIndex(columnas[2]);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            cuenta.add(new AccountModel(cursor.getInt(id), cursor.getString(email), cursor.getString(pass)));
        }
        db.close();
        return cuenta;
    }
}
