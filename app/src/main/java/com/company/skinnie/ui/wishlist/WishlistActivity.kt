package com.company.skinnie.ui.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.skinnie.R
import com.company.skinnie.adapter.PopularAdapter
import com.company.skinnie.adapter.RecommendAdapter
import com.company.skinnie.data.local.WishlistProduct
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.response.ResponseRecommend
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