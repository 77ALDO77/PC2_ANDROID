package com.example.appclase6.utils

import android.app.Application
import android.content.Context
import com.example.appclase6.data.BD

class AppConfig:Application() {
    companion object{
        //declarar atributos
        lateinit var contexto:Context
        lateinit var CN:BD
    }

    override fun onCreate() {
        contexto=applicationContext
        CN=BD()
        super.onCreate()
    }
}