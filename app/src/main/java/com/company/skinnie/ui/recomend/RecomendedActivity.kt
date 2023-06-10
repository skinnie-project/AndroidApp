package com.company.skinnie.ui.recomend

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivityRecomendedBinding
import com.company.skinnie.ui.detail.DetailProductActivity

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

        recommendAdapter = RecommendAdapter { id ->
            startActivity(Intent(this, DetailProductActivity::class.java).apply {
                putExtra(DetailProductActivity.EXTRA_ID, id)
            })
        }

        binding.rvRecommend.apply {
            adapter = recommendAdapter
            layoutManager = LinearLayoutManager(this@RecomendedActivity)
        }

        val subcategory = "Semua subcategory"

        binding.tvIngredient1.setOnClickListener {
            goToFilterActivity(ingredient1, predict, subcategory)
        }

        binding.tvIngredient2.setOnClickListener {
            goToFilterActivity(ingredient2, predict, subcategory)
        }

        binding.tvIngredient3.setOnClickListener {
            goToFilterActivity(ingredient3, predict, subcategory)
        }

        binding.tvIngredient4.setOnClickListener {
            goToFilterActivity(ingredient4, predict, subcategory)
        }

        binding.loading.visibility = View.VISIBLE
        getData(predict!!)
    }

    private fun goToFilterActivity(ingredient: String?, predict: String?, subcategory: String?) {
        startActivity(Intent(this, FilterActivity::class.java).apply {
            putExtra(FilterActivity.EXTRA_INGREDIENTS, ingredient)
            putExtra(FilterActivity.EXTRA_PREDICT, predict)
            putExtra(FilterActivity.EXTRA_SUBCATEGORY, subcategory)
        })
    }

    private fun getData(query: String) {
        viewModel.setPredict(query).observe(this) {
            binding.loading.visibility = View.GONE
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