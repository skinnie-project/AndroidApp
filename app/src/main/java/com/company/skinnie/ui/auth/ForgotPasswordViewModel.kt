package com.company.skinnie.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.PayloadForgotPassword
import com.company.skinnie.data.response.ResponseForgotPassword
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel : ViewModel() {
    fun userForgotPassword(body: PayloadForgotPassword): LiveData<ResponseForgotPassword?> {
        val mutableLiveData = MutableLiveData<ResponseForgotPassword?>()

        provideRetrofit().forgot(body).enqueue(object : Callback<ResponseForgotPassword> {
            override fun onResponse(
                call: Call<ResponseForgotPassword>,
                response: Response<ResponseForgotPassword>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseForgotPassword>, t: Throwable) {
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}