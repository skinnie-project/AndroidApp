package com.company.skinnie.ui.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.company.skinnie.data.local.WishlistDao
import com.company.skinnie.data.local.WishlistProduct
import com.company.skinnie.data.local.WishlistRoom
import com.company.skinnie.data.response.ResponsePopularItem

class WishlistViewModel (application: Application): AndroidViewModel(application) {

    private val wishlistDao: WishlistDao?
    private val wishlistRoom: WishlistRoom? = WishlistRoom.getDatabase(application)

    init {
        wishlistDao = wishlistRoom?.wishlistDao()
    }

    fun getWishlist(): LiveData<List<ResponsePopularItem>>?{
        return wishlistDao?.getWishlist()
    }
}