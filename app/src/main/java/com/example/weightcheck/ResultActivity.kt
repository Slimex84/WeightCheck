package com.example.weightcheck

import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.weightcheck.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var textViewIMC: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var buttonRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        //There is a ? to say that if the function do not receive a Double, it will return a -1.0
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initComponents() {
        textViewIMC = findViewById(R.id.textViewIMC)
        textViewResult = findViewById(R.id.textViewResult)
        textViewDescription = findViewById(R.id.textViewDescription)
        buttonRecalculate = findViewById(R.id.buttonRecalculate)
    }

    private fun initListeners() {
        buttonRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        val decimalFormat = DecimalFormat("#.##")
        val imc = decimalFormat.format(result)
        textViewIMC.text = imc
        when (result) {
            in 0.00..18.50 -> { //Bajo peso
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.low_weight))
                textViewResult.text = getString(R.string.title_low_weight)
                textViewDescription.text = getString(R.string.description_low_weight)
            }

            in 18.51..24.99 -> { //Peso normal
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                textViewResult.text = getString(R.string.title_normal_weight)
                textViewDescription.text = getString(R.string.description_normal_weight)
            }

            in 25.00..29.99 -> { //Sobrepeso
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.high_weight))
                textViewResult.text = getString(R.string.title_high_weight)
                textViewDescription.text = getString(R.string.description_high_weight)
            }

            in 30.00..99.00 -> { //Obesidad
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                textViewResult.text = getString(R.string.title_obesity)
                textViewDescription.text = getString(R.string.description_obesity)
            }

            else -> {
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                textViewIMC.text = getString(R.string.error)
                textViewResult.text = getString(R.string.error)
                textViewDescription.text = getString(R.string.error)
            }
        }
    }
}