package com.company.skinnie.ui.recomend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponsePopularItem
import com.company.skinnie.data.response.ResponseRecommend
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel : ViewModel() {
    fun setPredict(query: String): LiveData<List<ResponsePopularItem?>?> {
        val mutableLiveData = MutableLiveData<List<ResponsePopularItem?>?>()

        provideRetrofit().getRecommendation(query).enqueue(object :
            Callback<List<ResponsePopularItem>> {
            override fun onResponse(
                call: Call<List<ResponsePopularItem>>,
                response: Response<List<ResponsePopularItem>>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<List<ResponsePopularItem>>,
                t: Throwable
            ) {
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}