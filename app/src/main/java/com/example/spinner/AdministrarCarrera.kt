package com.example.spinner

import android.database.DatabaseUtils
import android.widget.Toast
import java.lang.Exception

data class Carrera(var id:String, var name:String, var facultyName:String)

class AdministrarCarrera {

    val resultsArray = ArrayList<String>()

    //TODO-->se crea una carga inicial
    fun initialCharge(){

    }

    fun getAllName():ArrayList<String>?{
        try {
            val dataBase = AppSpinner.DATA_BASE.readableDatabase
            val numData = DatabaseUtils.queryNumEntries(dataBase,AppSpinner.DB_TABLE_CARRERA).toInt()

            if(numData > 0){
                val qry = "SELECT ${Contract.CollegeCareer.name} FROM ${AppSpinner.DB_TABLE_CARRERA}"
                val namesCursor = dataBase.rawQuery(qry,null)

                namesCursor.moveToFirst() //Estoy en el inicio de la tabla
                do {
                    resultsArray.add(namesCursor.getString(namesCursor.getColumnIndex(Contract.CollegeCareer.name)))
                }while (namesCursor.moveToNext())
            }
            dataBase.close()
            return resultsArray
        }catch (ex:Exception){
            Toast.makeText(AppSpinner.CONTEXT,"No se pudo recuperar las carreras",
                Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun addCareer(name:String){

    }

    fun deleteCareer(name:String){

    }
}