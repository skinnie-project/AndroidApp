package com.company.skinnie.ui.recomend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponseFilter
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterViewModel : ViewModel() {
    fun setFilter(
        ingredient: String,
        subcategory: String,
        predicted: String
    ): LiveData<List<ResponseFilter.ResponseFilterItem?>?> {
        val mutableLiveData = MutableLiveData<List<ResponseFilter.ResponseFilterItem?>?>()

        provideRetrofit().getFilter(ingredient, subcategory, predicted).enqueue(object :
            Callback<List<ResponseFilter.ResponseFilterItem>> {
            override fun onResponse(
                call: Call<List<ResponseFilter.ResponseFilterItem>>,
                response: Response<List<ResponseFilter.ResponseFilterItem>>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<List<ResponseFilter.ResponseFilterItem>>,
                t: Throwable
            ) {
                mutableLiveData.value = null
            }

        })
        return mutableLiveData
    }
}