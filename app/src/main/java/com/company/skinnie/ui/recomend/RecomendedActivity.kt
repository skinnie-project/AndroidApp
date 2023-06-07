package com.company.skinnie.ui.recomend

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivityRecomendedBinding

class RecomendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendedBinding
    private lateinit var recommendAdapter: RecommendAdapter
    private val viewModel: RecommendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val predict = intent.getStringExtra(EXTRA_PREDICT)
        val ingredient1 = intent.getStringExtra(EXTRA_INGREDIENT1)
        val ingredient2 = intent.getStringExtra(EXTRA_INGREDIENT2)
        val ingredient3 = intent.getStringExtra(EXTRA_INGREDIENT3)
        val ingredient4 = intent.getStringExtra(EXTRA_INGREDIENT4)

        binding.tvIngredient1.text = ingredient1
        binding.tvIngredient2.text = ingredient2
        binding.tvIngredient3.text = ingredient3
        binding.tvIngredient4.text = ingredient4

        binding.tvRecomended.text = predict

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        recommendAdapter = RecommendAdapter()
        binding.rvRecommend.apply {
            adapter = recommendAdapter
            layoutManager = LinearLayoutManager(this@RecomendedActivity)
        }
        getUser(predict!!)
    }

    private fun getUser(query: String) {
        viewModel.setPredict(query).observe(this) {
            if (it != null) {
                recommendAdapter.setRecommend(it)
            }
        }

    }

    companion object {
        const val EXTRA_INGREDIENT1 = "extra_ingredient_1"
        const val EXTRA_INGREDIENT2 = "extra_ingredient_2"
        const val EXTRA_INGREDIENT3 = "extra_ingredient_3"
        const val EXTRA_INGREDIENT4 = "extra_ingredient_4"
        const val EXTRA_PREDICT = "extra_predict"
    }
}