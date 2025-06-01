package com.example.appclase6.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appclase6.utils.AppConfig

class BD : SQLiteOpenHelper(AppConfig.contexto, "consorcio.bd", null, 1) {

    companion object {
        private const val CREATE_TABLE_SUPERVISOR = """
            CREATE TABLE IF NOT EXISTS tb_supervisor (
                cod INTEGER PRIMARY KEY AUTOINCREMENT,
                nom VARCHAR(30),
                pat VARCHAR(40),
                mat VARCHAR(40),
                sue DOUBLE,
                turno VARCHAR(15),
                foto TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SUPERVISOR)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS tb_supervisor")
        db?.let { onCreate(it) }
    }

    fun createTableIfNotExists() {
        val db = this.writableDatabase
        db.execSQL(CREATE_TABLE_SUPERVISOR)
    }
}