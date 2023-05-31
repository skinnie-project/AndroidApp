package com.company.skinnie.ui.recomend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.databinding.ActivityRecomendedBinding

class RecomendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}