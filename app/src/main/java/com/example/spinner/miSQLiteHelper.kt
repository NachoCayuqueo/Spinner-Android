package com.example.spinner

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper:SQLiteOpenHelper(
    AppSpinner.CONTEXT,AppSpinner.DB_NAME,null,AppSpinner.VERSION) {

    val qryCreateTable = "CREATE TABLE ${AppSpinner.DB_TABLE_FACULTAD}("+
            "${Contract.Faculty.facultyID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "${Contract.Faculty.name} TEXT,"+
            "${Contract.Faculty.career} TEXT)"

    val qryCreateTable2 = "CREATE TABLE ${AppSpinner.DB_TABLE_CARRERA}("+
            "${Contract.CollegeCareer.id} INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "${Contract.CollegeCareer.name} TEXT,"+
            "${Contract.CollegeCareer.facultyName} TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(qryCreateTable)
        db!!.execSQL(qryCreateTable2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${AppSpinner.DB_TABLE_FACULTAD}")
        db.execSQL("DROP TABLE IF EXISTS ${AppSpinner.DB_TABLE_CARRERA}")
        onCreate(db)
    }

}