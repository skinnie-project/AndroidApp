package com.company.skinnie.ui.saran

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivitySaranBinding

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

        binding.btnKirimSaran.setOnClickListener {
            when {
                binding.etNamaSaran.text.toString().isEmpty() -> binding.etNamaSaran.error =
                    "Masukkan nama Anda"
                binding.etEmailSaran.text.toString().isEmpty() -> binding.etEmailSaran.error =
                    "Masukkan email Anda"
                binding.etSaran.text.toString().isEmpty() -> binding.etSaran.error =
                    "Masukkan saran Anda"
                else -> {
                    binding.etNamaSaran.text?.clear()
                    binding.etEmailSaran.text?.clear()
                    binding.etSaran.text?.clear()
                    Toast.makeText(this, "Terima kasih atas saran Anda", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}