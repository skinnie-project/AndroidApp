package com.company.skinnie.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.company.skinnie.R
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.databinding.ActivityDetailProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val viewModel: DetailProductViewModel by viewModels()
    private var product: ResponsePopularItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_ID, 0)

        //check wishlist
        var isWishlist = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkWishlist(id)
            withContext(Dispatchers.Main){
                if (count != null) {
                    if (count > 0) {
                        //action fab kl di klik
                        binding.fabFavorit.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorit.context, R.drawable.ic_favorite))
                        isWishlist = true
                    } else {
                        //action fab kl di unclick
                        binding.fabFavorit.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorit.context, R.drawable.ic_baseline_favorite_border_24))
                        isWishlist = false
                    }
                }
            }
        }

        binding.fabFavorit.setOnClickListener {
            isWishlist = !isWishlist
            if (isWishlist) {
                if (product != null) {
                    viewModel.addWishlist(product!!)
                    Toast.makeText(this, "Ditambahkan ke wishlist", Toast.LENGTH_SHORT).show()
                    binding.fabFavorit.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorit.context, R.drawable.ic_favorite))
                }
            } else {
                if (product != null) {
                    viewModel.deleteWishlist(product?.id!!)
                    Toast.makeText(this, "Dihapus dari wishlist", Toast.LENGTH_SHORT).show()
                    binding.fabFavorit.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorit.context, R.drawable.ic_baseline_favorite_border_24))
                }

            }
        }



        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.loading.visibility = android.view.View.VISIBLE
        getProduct(id)

    }

    private fun getProduct(id: Int) {
        viewModel.setDetail(id).observe(this) {
            if (it != null) {
                product = it
                binding.loading.visibility = android.view.View.GONE
                Glide.with(this)
                    .load(it.urlNew)
                    .into(binding.ivProduct)
                binding.tvBrandProduct.text = it.brand
                binding.tvNameProduct.text = it.productName
                binding.tvDescProduct.text = it.description
                binding.btnPrice.text = it.price.toString()
                binding.tvIngredient.text = it.ingredients
                binding.tvTutorial.text = it.howToUse
                if (it.price == null){
                    binding.btnPrice.text = resources.getString(R.string.dummy_price, "0")
                }else{
                    binding.btnPrice.text = resources.getString(R.string.dummy_price, it.price.toString())
                }
                binding.tvIngredient.text = it.ingredients
                binding.tvTutorial.text = it.howToUse
                binding.tvRating.text = it.rate
                binding.tvReviewed.text = resources.getString(R.string.reviewed, it.reviewed.toString())
            }

        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}