package com.company.skinnie.ui.wishlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.R
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.databinding.ActivityWishlistBinding
import com.company.skinnie.ui.detail.DetailProductActivity

class WishlistActivity : AppCompatActivity(), (Int) -> Unit {

    private lateinit var binding: ActivityWishlistBinding
    private lateinit var adapter: RecommendAdapter
    private lateinit var viewModel: WishlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecommendAdapter(this)

        viewModel = ViewModelProvider(this).get(WishlistViewModel::class.java)

        binding.toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.apply {
            rvWishlist.setHasFixedSize(true)
            rvWishlist.layoutManager = LinearLayoutManager(this@WishlistActivity)
            rvWishlist.adapter = adapter
        }

        viewModel.getWishlist()?.observe(this) {
            if (it != null) {
                adapter.setRecommend(it)
            }
        }
    }


    override fun invoke(id: Int) {
        startActivity(Intent(this, DetailProductActivity::class.java).apply {
            putExtra(DetailProductActivity.EXTRA_ID, id)
        })
    }


}