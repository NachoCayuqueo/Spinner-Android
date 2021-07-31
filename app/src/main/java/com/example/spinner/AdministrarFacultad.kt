package com.example.spinner

import android.database.DatabaseUtils
import android.widget.Toast
import java.lang.Exception

data class Faculty(var facultyID:Int, var name:String, var career:String)

class AdministrarFacultad {

    val resultsArray = ArrayList<String>()

    //TODO-->se crea una carga inicial
    fun initialCharge(){
        val arrayFacultiesNames = arrayOf("Informatica","Ingenieria","Economia y Administración")

        for(i in arrayFacultiesNames.indices) {
            val name = arrayFacultiesNames[i]
            val arrayCarees = getArray(name)
            for (j in arrayCarees.indices) {
                val careerName = arrayCarees[j]
                val faculty = Faculty(0, name, careerName)
                addFaculty(faculty)
            }
        }
    }
    private fun getArray(name: String): ArrayList<String>{
        return when(name){
            "Informatica" -> arrayListOf("Profesorado En Informatica",
                             "Lic. En Cs de la Computación","Tecnicatura en Desarrollo WEB")
            "Ingenieria" -> arrayListOf("Ingeniería Quimica","Ingeniería Fisica","Ingeniería Civil")
            "Economia y Administración" -> arrayListOf("Lic en Administración","Lic en Matemática",
                                            "Contador Publico Nacional")
            else -> arrayListOf()
        }
    }

    fun getAllName(): ArrayList<String>?{
        try {
            val namesArray = arrayListOf<String>()

            val database = AppSpinner.DATA_BASE.readableDatabase
            val numData = DatabaseUtils.queryNumEntries(database,AppSpinner.DB_TABLE_FACULTAD).toInt()

            if(numData > 0){
                val qry = "SELECT ${Contract.Faculty.name} FROM ${AppSpinner.DB_TABLE_FACULTAD}"
                val names = database.rawQuery(qry,null)

                names.moveToFirst()
                do {
                    val nameString = names.getString(names.getColumnIndex(Contract.Faculty.name))
                    val indice = namesArray.indexOf(nameString)
                    if(indice == -1) //si el indice es -1 no esta el elemento(evito repetidos)
                        namesArray.add(nameString)
                }while (names.moveToNext())
            }
            database.close()
            return namesArray
        }catch (ex:Exception){
            Toast.makeText(AppSpinner.CONTEXT,
                "No se recuperaron datos",Toast.LENGTH_SHORT).show()
            return null
        }
    }

    fun getAllCareersOfFaculty(name:String): ArrayList<String>?{
        try{
            val database = AppSpinner.DATA_BASE.readableDatabase

            /**Se define una proyección que especifique las columnas que se utilizaran**/
            val projection = arrayOf(Contract.Faculty.career)

            /**Filtrar Resultados**/
            val selection = "${Contract.Faculty.name} = ?"
            val selectionArg = arrayOf(name)

            /**Orden de los Elementos**/
            val sortOrden = "${Contract.Faculty.career} ASC"

            val cursor = database.query(
                AppSpinner.DB_TABLE_FACULTAD,
                projection,
                selection,
                selectionArg,
                null,
                null,
                sortOrden
            )
            if(cursor.count > 0){
                cursor.moveToFirst()
                do{
                    val careerName = cursor.getString(0)
                    resultsArray.add(careerName)
                }while (cursor.moveToNext())
            }

            database.close()
            return resultsArray
        }catch (ex:Exception){
            return null
        }
    }

    fun addFaculty(faculty:Faculty){
        try{
            val database = AppSpinner.DATA_BASE.writableDatabase

            val qry = "INSERT INTO ${AppSpinner.DB_TABLE_FACULTAD} ("+
                    "${Contract.Faculty.name}, ${Contract.Faculty.career})"+
                    "VALUES ( '${faculty.name}','${faculty.career}' );"

            database.execSQL(qry)
            database.close()
        }catch (ex:Exception){
            Toast.makeText(AppSpinner.CONTEXT,"Error al intentar guardar una nueva facultad",
                Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteFaculty(name:String){
        try{
            val db = AppSpinner.DATA_BASE.writableDatabase
            val qry = "DELETE FROM ${AppSpinner.DB_TABLE_FACULTAD} " +
                    "WHERE ${Contract.Faculty.name} == '$name';"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            Toast.makeText(AppSpinner.CONTEXT,
                "No se elimino el dato seleccionado",Toast.LENGTH_SHORT).show()
        }
    }

    fun facultyExists():Boolean{
        var exito = false
        try {
            val database = AppSpinner.DATA_BASE.readableDatabase
            val numData = DatabaseUtils.queryNumEntries(database,AppSpinner.DB_TABLE_FACULTAD).toInt()

            if(numData > 0)
                exito = true

            database.close()
        }catch(ex:Exception){
            Toast.makeText(AppSpinner.CONTEXT,"Problemas al leer la base de datos",
                Toast.LENGTH_SHORT).show()
        }
        return exito
    }
}