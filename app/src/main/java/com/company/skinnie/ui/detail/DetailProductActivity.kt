package com.company.skinnie.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.company.skinnie.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding

    private val viewModel: DetailProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_ID, 0)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.loading.visibility = android.view.View.VISIBLE
        getProduct(id)

    }

    private fun getProduct(id: Int) {
        viewModel.setDetail(id).observe(this) {
            if (it != null) {
                binding.loading.visibility = android.view.View.GONE
                Glide.with(this)
                    .load(it[0]!!.urlNew)
                    .into(binding.ivProduct)
                binding.tvBrandProduct.text = it[0]!!.brand
                binding.tvNameProduct.text = it[0]!!.productName
                binding.tvDescProduct.text = it[0]!!.description
                binding.btnPrice.text = it[0]!!.price.toString()
                binding.tvIngredient.text = it[0]!!.ingredients
                binding.tvTutorial.text = it[0]!!.howToUse
            }

        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}