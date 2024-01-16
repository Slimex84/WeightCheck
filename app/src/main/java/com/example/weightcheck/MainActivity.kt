package com.example.weightcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
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
    private fun initComponents() {
        showImcInfoDialog()
        buttonCalculate = findViewById(R.id.buttonCalculate)
    }

    //Inicializamos los listeners
    private fun initListeners() {
        //Botón calcular
        buttonCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    //Mostrar alerta de información acerca del IMC (váliido en adultos)
    private fun showImcInfoDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setIcon(R.drawable.bell)
            setTitle("AVISO!")
            setMessage("El IMC no es un cálculo 100% preciso, ya que no diagnostica la " +
                    "grasa corporal ni la salud de un individuo. El IMC es una fórmula que relaciona " +
                    "el peso y la altura de una persona, y se utiliza para clasificar el estado " +
                    "nutricional según la Organización Mundial de la Salud. Además, el cálculo aquí " +
                    "presente se limita a personas mayores de 20 años.")
        }.create().show()
    }
}