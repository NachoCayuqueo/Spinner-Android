package com.example.spinner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class ResultsActivity : AppCompatActivity() {

    private val facultyAdmin = AdministrarFacultad()
    lateinit var spinnerArray: ArrayList<String>
    private var careerList= ArrayList<String>()
    private var nameFaculty: String = ""
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        mostrar()
    }

    private fun mostrar(){
        loadInitialData()
        showSpinner()
        buttonsActions()
    }
    private fun loadInitialData(){
        if(!facultyAdmin.facultyExists())
            facultyAdmin.initialCharge()//cargo unos datos al inicio
    }
    private fun showSpinner(){
        val spinner = findViewById<Spinner>(R.id.spinnerID)

        //TODO--> Se muestra el spinner
        getSpinnerList()
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerArray)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        //TODO--> Se muestra el listado dependiendo la posición en el spinner
        spinner.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //TODO--> cuando se selecciona un item del spinner
                    nameFaculty = parent?.getItemAtPosition(position) as String
                    createListByPosition(nameFaculty)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }

    private fun buttonsActions(){

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){result ->
            if(result.resultCode == Activity.RESULT_OK){
                showSpinner()
            }
        }

        clickButtonNewCareer()
        clickButtonNewFaculty()
        clickButtonGoBack()
    }

    private fun clickButtonNewCareer(){

        val newCareerButton = findViewById<Button>(R.id.newCareerButtonID)
        newCareerButton.setOnClickListener {
            val intent = Intent(this,NewCareerActivity::class.java)
            intent.putExtra("nameFaculty",nameFaculty)
            resultLauncher.launch(intent)
        }
    }

    private fun clickButtonNewFaculty(){
        val newFacultyButton = findViewById<Button>(R.id.newFacultyButtonID)
        newFacultyButton.setOnClickListener {
            val intent = Intent(this,NewFacultyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickButtonGoBack(){
        val goBackButton = findViewById<Button>(R.id.goBackButtonID)
        goBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getSpinnerList(){
        spinnerArray = facultyAdmin.getAllName()!!
    }

    private fun createListByPosition(item:String){
        careerList.clear()
        careerList = facultyAdmin.getAllCareersOfFaculty(item)!!

        val listAdapter = ArrayAdapter(
            AppSpinner.CONTEXT,
            android.R.layout.simple_list_item_1,
            careerList
        )
        val listView = findViewById<ListView>(R.id.listViewID)
        listView.adapter = listAdapter
    }
}