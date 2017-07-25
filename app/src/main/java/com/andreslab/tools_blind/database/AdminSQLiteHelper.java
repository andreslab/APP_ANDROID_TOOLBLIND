package com.andreslab.tools_blind.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by macbookpro on 7/17/17.
 */

public class AdminSQLiteHelper extends SQLiteOpenHelper{

    public AdminSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //config permissors
        sqLiteDatabase.execSQL("CREATE TABLE permisos (id INTEGER PRIMARY KEY, permiso TEXT, acceso INTEGER);"); //1 si, 0 no
        //config contacto
        sqLiteDatabase.execSQL("CREATE TABLE contactos (id INTEGER PRIMARY KEY, nombre TEXT, telefono TEXT, correo TEXT);");
        //config cuenta de correo
        sqLiteDatabase.execSQL("CREATE TABLE cuenta (id INTEGER PRIMARY KEY, email TEXT, pass TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS permisos");
        sqLiteDatabase.execSQL("CREATE TABLE permisos (id INTEGER PRIMARY KEY, permiso TEXT, acceso INTEGER);");

        //config contacto
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contactos");
        sqLiteDatabase.execSQL("CREATE TABLE contactos (id INTEGER PRIMARY KEY, nombre TEXT, telefono TEXT, correo TEXT);");

        //config cuenta
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cuenta");
        sqLiteDatabase.execSQL("CREATE TABLE cuenta (id INTEGER PRIMARY KEY, email TEXT, pass TEXT);");
    }
}
