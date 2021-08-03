package com.example.spinner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewFacultyActivity : AppCompatActivity() {

    val facultyAdmin = AdministrarFacultad()
    lateinit var nameFaculty: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_faculty)

        completarFormulario()
    }

    private fun completarFormulario(){

        buttonsActions()
    }

    private fun buttonsActions(){
        clickNewFacultyButton()
        clickGoBackButton()
    }

    private fun clickNewFacultyButton(){
        val enterButton = findViewById<Button>(R.id.enterButtonID)

        enterButton.setOnClickListener {
            val nameFaculty = getFacultyName()
            if(nameFaculty!=""){
                if(!facultyAdmin.existFaculty(nameFaculty)){
                    val intent = Intent(this,NewCareerActivity::class.java)
                    intent.putExtra("nameFaculty",nameFaculty)
                    startActivity(intent)
                    setResult(Activity.RESULT_OK)
                    finish()
                }else{
                    Toast.makeText(this,"Facultad existente. Ingrese nuevo nombre",
                        Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Debe ingresar un nombre",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickGoBackButton() {
        val goBackButton = findViewById<Button>(R.id.goBackButtonID)
        goBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getFacultyName():String{
        nameFaculty = findViewById<EditText>(R.id.nameFacultyID)
        return nameFaculty.text.toString()
    }
}