package com.company.skinnie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponseArticleItem
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _popular = MutableLiveData<List<ResponsePopularItem>>()
    val popular: LiveData<List<ResponsePopularItem>> = _popular

    private val _article = MutableLiveData<List<ResponseArticleItem>>()
    val article: LiveData<List<ResponseArticleItem>> = _article


    init {
        setPopular()
        setArticle()
    }

    fun setPopular() {
        val client = ApiConfig.provideRetrofit().getPopular()
        client.enqueue(object : Callback<List<ResponsePopularItem>>{
            override fun onResponse(
                call: Call<List<ResponsePopularItem>>,
                response: Response<List<ResponsePopularItem>>
            ) {
                if (response.isSuccessful){
                    _popular.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<ResponsePopularItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setArticle() {
        val client = ApiConfig.provideRetrofit().getArticle()
        client.enqueue(object : Callback<List<ResponseArticleItem>>{
            override fun onResponse(
                call: Call<List<ResponseArticleItem>>,
                response: Response<List<ResponseArticleItem>>
            ) {
                if (response.isSuccessful){
                    _article.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<ResponseArticleItem>>, t: Throwable) {

            }

        })
    }


    companion object{
        private const val TAG = "HomeViewModel"

    }

}