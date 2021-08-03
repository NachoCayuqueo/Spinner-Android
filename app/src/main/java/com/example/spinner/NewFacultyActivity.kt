package com.example.spinner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewFacultyActivity : AppCompatActivity() {

    lateinit var nameFaculty: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_faculty)

        completarFormulario()
    }

    private fun completarFormulario(){

        clickNewCareerButton()
        clickGoBackButton()
    }

    private fun clickNewCareerButton() {
        val careerButton = findViewById<Button>(R.id.careerButtonID)
        careerButton.setOnClickListener {
            val name = getFacultyName()
            if(name != ""){
                val intent = Intent(this,NewCareerActivity::class.java)
                intent.putExtra("nameFaculty",name)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Se debe ingresar un nombre",
                    Toast.LENGTH_SHORT).show()
                nameFaculty.requestFocus()
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(nameFaculty,InputMethodManager.SHOW_IMPLICIT)
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