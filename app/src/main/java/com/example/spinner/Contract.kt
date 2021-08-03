package com.example.spinner

import android.provider.BaseColumns

class Contract {
    class Faculty: BaseColumns{
        companion object{
            val facultyID = "ID_facultad"
            val name = "nombre"
            val career = "carrera"
        }
    }
}