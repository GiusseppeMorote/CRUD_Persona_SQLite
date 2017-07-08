package com.cibertec.pc04_santoscordova;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alumno801 on 1/07/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

   String tabla_persona = "CREATE TABLE PERSONA(ID INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT, EDAD TEXT, TELEFONO TEXT, DOCUMENTO TEXT, DIRECCION TEXT);";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(tabla_persona);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
