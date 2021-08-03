package com.example.spinner

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class NewCareerActivity : AppCompatActivity() {

    private val facultyAdmin = AdministrarFacultad()
    private var nameFaculty: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_career)

        crearCarrera()
    }

    private fun crearCarrera(){
        addSubtitle()
        clickNewCareerButton()
        clickCancelButton()
    }

    private fun addSubtitle(){
        val subtitle = findViewById<TextView>(R.id.subtitleID)
        nameFaculty = intent.getStringExtra("nameFaculty").toString()
        subtitle.text = nameFaculty
    }

    private fun clickNewCareerButton(){
        val enterButton = findViewById<Button>(R.id.enterButtonID)
        enterButton.setOnClickListener {
            val nameCareer= getCareerName()
            if(nameCareer!=""){
                addCareerToDataBase(nameFaculty,nameCareer)

                setResult(Activity.RESULT_OK)
                finish()
            }else{
                Toast.makeText(this,"Debe ingresar una carrera",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addCareerToDataBase(nameFaculty: String, nameCareer: String) {
        val newFaculty = Faculty(0,nameFaculty,nameCareer)
        facultyAdmin.addFaculty(newFaculty)
    }

    private fun getCareerName(): String {
        val name = findViewById<EditText>(R.id.nameCareerID)
        return name.text.toString()
    }

    private fun clickCancelButton(){
        val cancelButton = findViewById<Button>(R.id.cancelButtonID)
        cancelButton.setOnClickListener {
            onBackPressed()
        }
    }
}