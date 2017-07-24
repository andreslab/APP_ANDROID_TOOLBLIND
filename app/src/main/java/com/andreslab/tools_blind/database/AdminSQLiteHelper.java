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
        //config email
        sqLiteDatabase.execSQL("CREATE TABLE cuentas_de_correos (id INTEGER PRIMARY KEY, nombre TEXT, correo TEXT, pass TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS permisos");
        sqLiteDatabase.execSQL("CREATE TABLE permisos (id INTEGER PRIMARY KEY, permiso TEXT, acceso INTEGER);");

        //config email
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cuentas_de_correos");
        sqLiteDatabase.execSQL("CREATE TABLE cuentas_de_correos (id INTEGER PRIMARY KEY, nombre TEXT, correo TEXT, pass TEXT);");

    }
}
