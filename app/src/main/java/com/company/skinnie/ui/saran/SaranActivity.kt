package com.company.skinnie.ui.saran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivitySaranBinding
import com.company.skinnie.databinding.ActivityWishlistBinding

class SaranActivity : AppCompatActivity() {

     private lateinit var binding: ActivitySaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saran)

        binding = ActivitySaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar4.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}