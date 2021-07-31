package com.example.spinner

import android.app.Application
import android.content.Context

class AppSpinner: Application() {
    companion object{
        lateinit var CONTEXT: Context
        lateinit var DATA_BASE: miSQLiteHelper

        val DB_NAME = "database.db"
        val VERSION = 1

        val DB_TABLE_FACULTAD = "tbFacultad"
        val DB_TABLE_CARRERA = "tbCarrera"
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        DATA_BASE = miSQLiteHelper()
    }
}