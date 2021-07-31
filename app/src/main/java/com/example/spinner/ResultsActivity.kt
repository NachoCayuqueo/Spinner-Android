package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ResultsActivity : AppCompatActivity() {

    val facultyAdmin = AdministrarFacultad()
    lateinit var spinnerArray: ArrayList<String>
    var careerList= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        mostrar()
    }
    fun mostrar(){
        loadInitialData()
        showSpinner()
        clickButtonNewFaculty()

    }
    private fun loadInitialData(){
        if(!facultyAdmin.facultyExists())
            facultyAdmin.initialCharge()//cargo unos datos al inicio
        else
            println("Tendria que entrar aca la segunda vez")
    }
    fun showSpinner(){
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
                    val item = parent?.getItemAtPosition(position) as String
                    createListByPosition(item)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
    }

    fun clickButtonNewFaculty(){
        val goBackButton = findViewById<Button>(R.id.goBackID)
        goBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getSpinnerList(){
        spinnerArray = facultyAdmin.getAllName()!!
    }

    private fun createListByPosition(item:String){
        //tiene q limpiar aca y desp pedir los datos nuevos
        careerList.clear()
        careerList = facultyAdmin.getAllCareersOfFaculty(item)!!

        var listAdapter = ArrayAdapter(
            AppSpinner.CONTEXT,
            android.R.layout.simple_list_item_1,
            careerList
        )
        val listView = findViewById<ListView>(R.id.listViewID)
        listView.adapter = listAdapter
    }
}