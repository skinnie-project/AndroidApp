package com.company.skinnie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    private val _product = MutableLiveData<List<ResponsePopularItem>>()
    val product: LiveData<List<ResponsePopularItem>> = _product


    fun findProduct(query: String) {
        val client = ApiConfig.provideRetrofit().getProduct(query)
        client.enqueue(object : Callback<List<ResponsePopularItem>> {
            override fun onResponse(
                call: Call<List<ResponsePopularItem>>,
                response: Response<List<ResponsePopularItem>>
            ) {
                if (response.isSuccessful) {
                    _product.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<ResponsePopularItem>>, t: Throwable) {

            }

        })
    }

    companion object {
        private const val query = "product"
    }
}