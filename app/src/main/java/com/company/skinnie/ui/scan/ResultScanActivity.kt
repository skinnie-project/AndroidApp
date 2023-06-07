package com.company.skinnie.ui.scan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.skinnie.Preferences
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivityResultScanBinding
import com.company.skinnie.ui.recomend.RecomendedActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class ResultScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //value getPreferences
        val predict = Preferences(this).getValues("predict")
        val ingredient1 = Preferences(this).getValues("ingredient1")
        val ingredient2 = Preferences(this).getValues("ingredient2")
        val ingredient3 = Preferences(this).getValues("ingredient3")
        val ingredient4 = Preferences(this).getValues("ingredient4")

        binding.tvResultScan.text = predict

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnNo.setOnClickListener {
            showBottomSheetDialog()
        }

        binding.btnYes.setOnClickListener {
            val intent = Intent(this, RecomendedActivity::class.java)
            intent.putExtra(RecomendedActivity.EXTRA_PREDICT, predict)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT1, ingredient1)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT2, ingredient2)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT3, ingredient3)
            intent.putExtra(RecomendedActivity.EXTRA_INGREDIENT4, ingredient4)
            startActivity(intent)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun showBottomSheetDialog() {
        val dialogView = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
        val dialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
        dialog.setContentView(dialogView)
        dialog.show()
    }
}