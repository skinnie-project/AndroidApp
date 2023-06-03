package com.company.skinnie.ui.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}