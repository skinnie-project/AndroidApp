package com.company.skinnie.ui.recomend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel : ViewModel() {

    private val _predict = MutableLiveData<List<ResponsePopularItem>>()
    val predict: LiveData<List<ResponsePopularItem>> = _predict


    fun setPredict(query: String){

        provideRetrofit().getRecommendation(query).enqueue(object :
            Callback<List<ResponsePopularItem>> {
            override fun onResponse(
                call: Call<List<ResponsePopularItem>>,
                response: Response<List<ResponsePopularItem>>
            ) {
                Log.d("TAG", "onResponse: ${response.body()?.size}")
                if (response.isSuccessful) {
                    _predict.postValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<List<ResponsePopularItem>>,
                t: Throwable
            ) {
                Log.d("TAG", "onResponse: ${t.message}")
            }
        })
    }
}