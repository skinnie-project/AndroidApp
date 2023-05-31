package com.company.skinnie.ui.scan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.databinding.ActivityInstructionBinding

class InstructionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstructionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, PreviewImageActivity::class.java))
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


}