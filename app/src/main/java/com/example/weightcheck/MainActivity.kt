package com.example.weightcheck

import android.content.Intent
import android.icu.text.DecimalFormat
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

    companion object {
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
        //initUI()
    }

    //Show alert about IMC info
    private fun showImcInfoDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setIcon(R.drawable.bell)
            setTitle("AVISO!")
            setMessage(
                "El IMC no es un cálculo 100% preciso, ya que no diagnostica la " +
                        "grasa corporal ni la salud de un individuo. El IMC es una fórmula que relaciona " +
                        "el peso y la altura de una persona, y se utiliza para clasificar el estado " +
                        "nutricional según la Organización Mundial de la Salud. Además, el cálculo aquí " +
                        "presente se limita a personas mayores de 20 años."
            )
        }.create().show()
    }

    //Components initializing
    private fun initComponents() {
        showImcInfoDialog()
        textViewHeight = findViewById(R.id.textViewHeight)
        rangeSliderHeight = findViewById(R.id.rangeSliderHeight)
        textViewWeight = findViewById(R.id.textViewWeight)
        rangeSliderWeight = findViewById(R.id.rangeSliderWeight)
        buttonCalculate = findViewById(R.id.buttonCalculate)
    }

    //Listeners initializing
    private fun initListeners() {
        //Slider init
        //Slider height
        rangeSliderHeight.addOnChangeListener { _, value, _ ->
            val decimalFormat = DecimalFormat("#.##")
            currentHeight = decimalFormat.format(value).toInt()
            textViewHeight.text = "$currentHeight cm"
        }
        //Slider weight
        rangeSliderWeight.addOnChangeListener { _, value, _ ->
            val decimalFormat = DecimalFormat("#.##")
            currentWeight = decimalFormat.format(value).toInt()
            textViewWeight.text = "$currentWeight kg"
        }

        //Calculate button
        buttonCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    //Math operation to calculate IMC on adults
    private fun calculateIMC(): Double {
        //val decimalFormat = DecimalFormat("#.##")
        return currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        //return decimalFormat.format(imc).toDouble()
    }

    //Function that shows the ResultActivity
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        //Se le agrega el extra que es la variable result de tipo Double
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }
}