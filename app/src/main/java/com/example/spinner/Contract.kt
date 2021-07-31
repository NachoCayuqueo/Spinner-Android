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
    class CollegeCareer{
        companion object{
            val id = "id"
            val name = "nombre"
            val facultyName = "facultad"
        }
    }
}