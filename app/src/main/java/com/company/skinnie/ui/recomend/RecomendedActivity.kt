package com.company.skinnie.ui.recomend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.R
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivityRecomendedBinding
import com.company.skinnie.model.ProductModel

class RecomendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendedBinding

    private val list = ArrayList<ProductModel>()

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

        val recomAdapter = RecommendAdapter()
        binding.rvRecommend.apply {
            adapter = recomAdapter
            layoutManager = LinearLayoutManager(this@RecomendedActivity)
        }
        dataDummy()
        recomAdapter.setRecommend(list)
    }

    private fun dataDummy() {
        list.addAll(
            listOf(
                ProductModel(
                    "Skintific",
                    "Produk ini memiliki kandungan yang dapat membantu mengatasi masalah kulitmu.",
                    R.drawable.dummy_product,
                    "Rp 100.000"
                ),
                ProductModel(
                    "Skintific",
                    "Produk ini memiliki kandungan yang dapat membantu mengatasi masalah kulitmu.",
                    R.drawable.dummy_product,
                    "Rp 100.000"
                ),
                ProductModel(
                    "Skintific",
                    "Produk ini memiliki kandungan yang dapat membantu mengatasi masalah kulitmu.",
                    R.drawable.dummy_product,
                    "Rp 100.000"
                ),
                ProductModel(
                    "Skintific",
                    "Produk ini memiliki kandungan yang dapat membantu mengatasi masalah kulitmu.",
                    R.drawable.dummy_product,
                    "Rp 100.000"
                ),
                ProductModel(
                    "Skintific",
                    "Produk ini memiliki kandungan yang dapat membantu mengatasi masalah kulitmu.",
                    R.drawable.dummy_product,
                    "Rp 100.000"
                ),
            )
        )
    }
}