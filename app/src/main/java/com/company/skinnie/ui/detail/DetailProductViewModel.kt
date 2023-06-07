package com.company.skinnie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponseDetail
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductViewModel : ViewModel() {
    fun setDetail(id: Int): LiveData<List<ResponseDetail?>?> {
        val mutableLiveData = MutableLiveData<List<ResponseDetail?>?>()

        provideRetrofit().getDetail(id).enqueue(object :
            Callback<List<ResponseDetail>> {
            override fun onResponse(
                call: Call<List<ResponseDetail>>,
                response: Response<List<ResponseDetail>>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ResponseDetail>>, t: Throwable) {
                mutableLiveData.value = null
            }

        })
        return mutableLiveData
    }
}