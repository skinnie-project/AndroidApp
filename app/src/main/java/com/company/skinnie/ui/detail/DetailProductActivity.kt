package com.company.skinnie.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.company.skinnie.R
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
        binding.loading.visibility = View.VISIBLE
        getProduct(id)

    }

    private fun getProduct(id: Int) {
        viewModel.setDetail(id).observe(this) {
            if (it != null) {
                binding.loading.visibility = View.GONE
                Glide.with(this)
                    .load(it[0]!!.urlNew)
                    .into(binding.ivProduct)
                binding.tvBrandProduct.text = it[0]!!.brand
                binding.tvNameProduct.text = it[0]!!.productName
                binding.tvDescProduct.text = it[0]!!.description
                if (it[0]!!.price == null){
                    binding.btnPrice.text = resources.getString(R.string.dummy_price, "0")
                }else{
                    binding.btnPrice.text = resources.getString(R.string.dummy_price, it[0]!!.price.toString())
                }
                binding.tvIngredient.text = it[0]!!.ingredients
                binding.tvTutorial.text = it[0]!!.howToUse
                binding.tvRating.text = it[0]!!.rate
                binding.tvReviewed.text = resources.getString(R.string.reviewed, it[0]!!.reviewed.toString())
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}