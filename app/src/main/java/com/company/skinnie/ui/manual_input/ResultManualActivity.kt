package com.company.skinnie.ui.manual_input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.skinnie.Preferences
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivityResultManualBinding
import com.company.skinnie.ui.recomend.RecomendedActivity
import com.company.skinnie.ui.scan.BottomSheet
import com.company.skinnie.ui.scan.InstructionActivity
import com.company.skinnie.ui.scan.PreviewImageActivity

class ResultManualActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultManualBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultManualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predict = intent.getStringExtra(EXTRA_PREDICT)
        val ingredient1 = Preferences(this).getValues("ingredient1")
        val ingredient2 = Preferences(this).getValues("ingredient2")
        val ingredient3 = Preferences(this).getValues("ingredient3")
        val ingredient4 = Preferences(this).getValues("ingredient4")


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
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT1, ingredient1)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT2, ingredient2)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT3, ingredient3)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT4, ingredient4)
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