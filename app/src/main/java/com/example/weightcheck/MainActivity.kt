package com.example.weightcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {

    //Needed variables
    private var currentWeight: Int = 60
    private var currentHeight: Int = 120

    //Needed components
    private lateinit var textViewHeight: TextView
    private lateinit var rangeSliderHeight: RangeSlider
    private lateinit var textViewWeight: TextView
    private lateinit var rangeSliderWeight: RangeSlider
    private lateinit var buttonCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Splash screen declaration
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Splash screen initializing
        splashScreen.setKeepOnScreenCondition { false }

        //Calling needed functions
        initComponents()
        initListeners()
    }

    //Components initializing
    private fun initComponents() {
        showImcInfoDialog()
        buttonCalculate = findViewById(R.id.buttonCalculate)
    }

    //Listeners initializing
    private fun initListeners() {
        //Botón calcular
        buttonCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    //Show alert about IMC info
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