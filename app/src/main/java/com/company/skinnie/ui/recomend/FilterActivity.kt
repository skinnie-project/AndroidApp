package com.company.skinnie.ui.recomend

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.adapter.FilterAdapter
import com.company.skinnie.databinding.ActivityFilterBinding
import com.company.skinnie.ui.detail.DetailProductActivity

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding
    private lateinit var filterAdapter: FilterAdapter
    private val viewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val predict = intent.getStringExtra(EXTRA_PREDICT)
        val ingredients = intent.getStringExtra(EXTRA_INGREDIENTS)
        val subcategory = intent.getStringExtra(EXTRA_SUBCATEGORY)

        filterAdapter = FilterAdapter{id ->
            startActivity(Intent(this, DetailProductActivity::class.java).apply {
                putExtra(DetailProductActivity.EXTRA_ID, id)
            })
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.rvListProduct.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(this@FilterActivity)
        }

        binding.loading.visibility = android.view.View.VISIBLE
        getData(ingredients!!, subcategory!!, predict!!)

    }

    private fun getData(ingredients: String,subcategory: String, predict: String) {
        viewModel.setFilter(ingredients, subcategory, predict).observe(this) {
            binding.loading.visibility = android.view.View.GONE
            if (it != null) {
                filterAdapter.setFilter(it)
            }
        }
    }

    companion object {
        const val EXTRA_SUBCATEGORY = "extra_subcategory"
        const val EXTRA_INGREDIENTS = "extra_ingredients"
        const val EXTRA_PREDICT = "extra_predict"
    }
}