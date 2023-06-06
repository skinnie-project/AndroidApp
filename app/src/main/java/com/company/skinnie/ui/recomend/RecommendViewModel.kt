package com.company.skinnie.ui.recomend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponseRecommend
import com.company.skinnie.data.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel : ViewModel() {
    fun setPredict(query: String): LiveData<List<ResponseRecommend.ResponseRecommendItem?>?> {
        val mutableLiveData = MutableLiveData<List<ResponseRecommend.ResponseRecommendItem?>?>()

        ApiConfig.provideRetrofit().getRecommendation(query).enqueue(object :
            Callback<List<ResponseRecommend.ResponseRecommendItem>> {
            override fun onResponse(
                call: Call<List<ResponseRecommend.ResponseRecommendItem>>,
                response: Response<List<ResponseRecommend.ResponseRecommendItem>>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<List<ResponseRecommend.ResponseRecommendItem>>,
                t: Throwable
            ) {
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}