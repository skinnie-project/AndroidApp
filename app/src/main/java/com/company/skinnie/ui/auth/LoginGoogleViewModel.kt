package com.company.skinnie.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.PayloadGoogle
import com.company.skinnie.data.response.ResponseGoogle
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginGoogleViewModel : ViewModel() {
    fun userGoogle(body: PayloadGoogle): LiveData<ResponseGoogle?> {
        val mutableLiveData = MutableLiveData<ResponseGoogle?>()

        provideRetrofit().google(body).enqueue(object : Callback<ResponseGoogle> {
            override fun onResponse(
                call: Call<ResponseGoogle>,
                response: Response<ResponseGoogle>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseGoogle>, t: Throwable) {
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}