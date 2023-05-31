package com.company.skinnie.ui.manual_input

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.databinding.ActivityManualInputBinding
import com.company.skinnie.ui.recomend.RecomendedActivity

class ManualInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManualInputBinding

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
        }
    }
}