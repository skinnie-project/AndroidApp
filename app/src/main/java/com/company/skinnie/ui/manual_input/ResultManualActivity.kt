package com.company.skinnie.ui.manual_input

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivityResultManualBinding
import com.company.skinnie.ui.recomend.RecomendedActivity
import com.company.skinnie.ui.scan.BottomSheet

class ResultManualActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultManualBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predict = intent.getStringExtra(EXTRA_PREDICT)


        when (predict) {
            "Berminyak" -> {
                binding.ivAnimationSkin.setImageDrawable(getDrawable(R.drawable.animation_oily))
            }
            "Kering" -> {
                binding.ivAnimationSkin.setImageDrawable(getDrawable(R.drawable.animation_dry))
            }
            else -> {
                binding.ivAnimationSkin.setImageDrawable(getDrawable(R.drawable.animation_normal))
            }
        }

        binding.tvResultScan.text = predict

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //buttom sheet
        val buttomSheetFragment = BottomSheet()
        binding.btnNo.setOnClickListener {
            buttomSheetFragment.show(supportFragmentManager, "BottomSheet")
            //showBottomSheetDialog()
        }

        binding.btnYes.setOnClickListener {
            val intent = Intent(this, RecomendedActivity::class.java)
            intent.putExtra(RecomendedActivity.EXTRA_PREDICT, predict)
            startActivity(intent)
            finish()
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val EXTRA_PREDICT = "extra_predict"
    }
}