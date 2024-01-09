package com.example.weightcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    //Declaración de variables
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        //Declaramos el splash screen
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializamos el splash screen
        splashScreen.setKeepOnScreenCondition { false }


        //Llamamos las funciones necesarias
        initComponents()
        initListeners()
    }

    //Inicializamos los componentes
    private fun initComponents(){
        buttonCalculate = findViewById(R.id.buttonCalculate)
    }

    //Inicializamos los listeners
    private fun initListeners(){
        //Botón calcular
        buttonCalculate.setOnClickListener {
            val intent = Intent(this, activity_result::class.java)
            startActivity(intent)
        }
    }
}