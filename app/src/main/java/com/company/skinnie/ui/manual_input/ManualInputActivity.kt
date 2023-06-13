package com.company.skinnie.ui.manual_input

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.databinding.ActivityManualInputBinding
import com.company.skinnie.ui.recomend.RecomendedActivity

class ManualInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManualInputBinding

    private var cfKulitNormal = 0.0
    private var cfKulitBerminyak = 0.0
    private var cfKulitKering = 0.0


    private val expertWeights = doubleArrayOf(
        0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8,  // Gejala kulit normal
        0.8, 0.8, 0.8, 0.8,  // Gejala kulit berminyak
        0.6, 0.6, 0.8, 0.6, 0.6 // Gejala kulit kering
    )

    private fun calculateNormal() {
        // CF untuk kulit normal //
        // Pertanyaan 1
        var hasil1 = 0.0
        val selectedRadioButtonId1 = binding.Qnormal1.checkedRadioButtonId
        if (selectedRadioButtonId1 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId1)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil1 = expertWeights[0] * selectedValue
        }

        // Pertanyaan 2
        var hasil2 = 0.0
        val selectedRadioButtonId2 = binding.Qnormal2.checkedRadioButtonId
        if (selectedRadioButtonId2 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId2)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil2 = expertWeights[1] * selectedValue
        }

        // Pertanyaan 3
        var hasil3 = 0.0
        val selectedRadioButtonId3 = binding.Qnormal3.checkedRadioButtonId
        if (selectedRadioButtonId3 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId3)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil3 = expertWeights[2] * selectedValue
        }

        // Pertanyaan 4
        var hasil4 = 0.0
        val selectedRadioButtonId4 =  binding.Qnormal4.checkedRadioButtonId
        if (selectedRadioButtonId4 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId4)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil4 = expertWeights[3] * selectedValue
        }

        // Pertanyaan 5
        var hasil5 = 0.0
        val selectedRadioButtonId5 =  binding.Qnormal5.checkedRadioButtonId
        if (selectedRadioButtonId5 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId5)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil5 = expertWeights[4] * selectedValue
        }

        // Pertanyaan 6
        var hasil6 = 0.0
        val selectedRadioButtonId6 =  binding.Qnormal6.checkedRadioButtonId
        if (selectedRadioButtonId6 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId6)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil6 = expertWeights[5] * selectedValue
        }

        // Pertanyaan 7
        var hasil7 = 0.0
        val selectedRadioButtonId7 =  binding.Qnormal7.checkedRadioButtonId
        if (selectedRadioButtonId7 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId7)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil7 = expertWeights[6] * selectedValue
        }
        val cfCombine1 = hasil1 + hasil2 * (1 - hasil1)
        val cfcombine2 = cfCombine1 + hasil3 * (1 - cfCombine1)
        val cfCombine3 = cfcombine2 + hasil4 * (1 - cfcombine2)
        val cfCombine4 = cfCombine3 + hasil5 * (1 - cfCombine3)
        val cfCombine5 = cfCombine4 + hasil6 * (1 - cfCombine4)
        cfKulitNormal = cfCombine5 + hasil7 * (1 - cfCombine5)
    }

    private fun calculateMinyak() {
        // Pertanyaan 1
        var hasil1 = 0.0
        val selectedRadioButtonId1 =  binding.Qminyak1.checkedRadioButtonId
        if (selectedRadioButtonId1 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId1)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil1 = expertWeights[7] * selectedValue
        }

        // Pertanyaan 2
        var hasil2 = 0.0
        val selectedRadioButtonId2 = binding.Qminyak2.checkedRadioButtonId
        if (selectedRadioButtonId2 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId2)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil2 = expertWeights[8] * selectedValue
        }

        // Pertanyaan 3
        var hasil3 = 0.0
        val selectedRadioButtonId3 = binding.Qminyak3.checkedRadioButtonId
        if (selectedRadioButtonId3 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId3)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil3 = expertWeights[9] * selectedValue
        }

        // Pertanyaan 4
        var hasil4 = 0.0
        val selectedRadioButtonId4 = binding.Qminyak4.checkedRadioButtonId
        if (selectedRadioButtonId4 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId4)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil4 = expertWeights[10] * selectedValue
        }
        val cfCombine1 = hasil1 + hasil2 * (1 - hasil1)
        val cfcombine2 = cfCombine1 + hasil3 * (1 - cfCombine1)
        cfKulitBerminyak = cfcombine2 + hasil4 * (1 - cfcombine2)
    }

    private fun calculateKering() {
        // Pertanyaan 1
        var hasil1 = 0.0
        val selectedRadioButtonId1 = binding.Qkering1.checkedRadioButtonId
        if (selectedRadioButtonId1 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId1)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil1 = expertWeights[11] * selectedValue
        }

        // Pertanyaan 2
        var hasil2 = 0.0
        val selectedRadioButtonId2 = binding.Qkering2.checkedRadioButtonId
        if (selectedRadioButtonId2 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId2)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil2 = expertWeights[12] * selectedValue
        }

        // Pertanyaan 3
        var hasil3 = 0.0
        val selectedRadioButtonId3 = binding.Qkering3.checkedRadioButtonId
        if (selectedRadioButtonId3 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId3)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil3 = expertWeights[13] * selectedValue
        }

        // Pertanyaan 4
        var hasil4 = 0.0
        val selectedRadioButtonId4 = binding.Qkering4.checkedRadioButtonId
        if (selectedRadioButtonId4 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId4)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil4 = expertWeights[14] * selectedValue
        }

        // Pertanyaan 5
        var hasil5 = 0.0
        val selectedRadioButtonId5 = binding.Qkering5.checkedRadioButtonId
        if (selectedRadioButtonId5 != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId5)
            val selectedValue = selectedRadioButton.text.toString().toDouble()
            hasil5 = expertWeights[15] * selectedValue
        }
        val cfCombine1 = hasil1 + hasil2 * (1 - hasil1)
        val cfcombine2 = cfCombine1 + hasil3 * (1 - cfCombine1)
        val cfcombine3 = cfcombine2 + hasil4 * (1 - cfcombine2)
        cfKulitKering = cfcombine3 + hasil5 * (1 - cfcombine3)
    }

    @SuppressLint("SetTextI18n")
    private fun findBiggest() {
//        val text_result =  findViewById<TextView>(R.id.text_result)
//        if (cfKulitNormal > cfKulitBerminyak && cfKulitNormal > cfKulitKering) {
//            text_result.text = "Hasil Akhir: Kulit Normal"
//        } else if (cfKulitBerminyak > cfKulitNormal && cfKulitBerminyak > cfKulitKering) {
//            text_result.text = "Hasil Akhir: Kulit Berminyak"
//        } else if (cfKulitKering > cfKulitNormal && cfKulitKering > cfKulitBerminyak) {
//            text_result.text = "Hasil Akhir: Kulit Kering"
//        } else {
//            text_result.text = "Hasil Akhir: None"
//        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateResult() {
//        val hasilNormal = findViewById<TextView>(R.id.text_result_normal)
//        val cfKulitNormalString = cfKulitNormal.toString()
//        hasilNormal.text = "Persentase Kulit Normal: $cfKulitNormalString"
//        val hasilMinyak = findViewById<TextView>(R.id.text_result_berminyak)
//        val cfKulitMinyakString = cfKulitBerminyak.toString()
//        hasilMinyak.text = "Persentase Kulit Minyak: $cfKulitMinyakString"
//        val hasilKering = findViewById<TextView>(R.id.text_result_kering)
//        val cfKulitKeringString = cfKulitKering.toString()
//        hasilKering.text = "Persentase Kulit Kering: $cfKulitKeringString"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, RecomendedActivity::class.java))
            calculateNormal()
            calculateMinyak()
            calculateKering()
            calculateResult()
            findBiggest()
        }
    }
}