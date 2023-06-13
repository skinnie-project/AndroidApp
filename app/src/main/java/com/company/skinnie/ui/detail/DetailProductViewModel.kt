package com.company.skinnie.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.local.WishlistDao
import com.company.skinnie.data.local.WishlistProduct
import com.company.skinnie.data.local.WishlistRoom
import com.company.skinnie.data.response.ResponseDetail
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductViewModel(application: Application) : AndroidViewModel(application) {

    private val wishlistDao: WishlistDao?
    private val wishlistRoom: WishlistRoom? = WishlistRoom.getDatabase(application)

    init {
        wishlistDao = wishlistRoom?.wishlistDao()
    }

    fun setDetail(id: Int): LiveData<ResponsePopularItem?> {
        val mutableLiveData = MutableLiveData<ResponsePopularItem?>()

        provideRetrofit().getDetail(id).enqueue(object :
            Callback<List<ResponsePopularItem>> {
            override fun onResponse(
                call: Call<List<ResponsePopularItem>>,
                response: Response<List<ResponsePopularItem>>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()?.firstOrNull()
                }
            }

            override fun onFailure(call: Call<List<ResponsePopularItem>>, t: Throwable) {
                mutableLiveData.value = null
            }

        })
        return mutableLiveData
    }


    fun addWishlist(id: ResponsePopularItem) {
        CoroutineScope(Dispatchers.IO).launch {
//            val wishlist = WishlistProduct(id)
            wishlistDao?.insert(id)
        }
    }

    fun deleteWishlist(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            wishlistDao?.delete(id)
        }
    }

    suspend fun checkWishlist(id: Int) = wishlistDao?.checkWishlist(id)

}