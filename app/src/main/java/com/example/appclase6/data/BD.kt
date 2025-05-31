package com.example.appclase6.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appclase6.utils.AppConfig

class BD:SQLiteOpenHelper(AppConfig.contexto,
    "consorcio.bd",null,1) {
    override fun onCreate(p0: SQLiteDatabase) {
        //crear tablas
        p0.execSQL( "create table tb_docente"+
                        "(" +
                            "cod integer primary key autoincrement," +
                            "nom varchar(30)," +
                            "pat varchar(40)," +
                            "mat varchar(40)," +
                            "sue double,"+
                            "hijos int,"+
                            "sexo varchar(15),"+
                            "foto varchar(20)"+
                        ")"
        )
        //registros
        p0.execSQL("insert into tb_docente values(null,'Ana','Soto','Ayala',5800.7,1,'Femenino','d1')")
        p0.execSQL("insert into tb_docente values(null,'Luz','Mora','Palacios',4500.0,1,'Femenino','d2')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}