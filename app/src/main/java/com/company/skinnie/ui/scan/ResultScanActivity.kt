package com.company.skinnie.ui.scan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.Preferences
import com.company.skinnie.databinding.ActivityResultScanBinding
import com.company.skinnie.ui.manual_input.ManualInputActivity
import com.company.skinnie.ui.recomend.RecomendedActivity

class ResultScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //value getPreferences
        val predict = Preferences(this).getValues("predict")

        binding.tvResultScan.text = predict

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnNo.setOnClickListener {
            startActivity(Intent(this, ManualInputActivity::class.java))
        }

        binding.btnYes.setOnClickListener {
            startActivity(Intent(this, RecomendedActivity::class.java))
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}