package com.company.skinnie.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.PayloadLogin
import com.company.skinnie.data.response.ResponseLogin
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){

    fun userLogin(body: PayloadLogin): LiveData<ResponseLogin?>{
        val mutableLiveData = MutableLiveData<ResponseLogin?>()

        provideRetrofit().login(body).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>){
                if (response.isSuccessful){
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable){
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}