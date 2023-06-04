package com.company.skinnie.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.PayloadRegister
import com.company.skinnie.data.response.ResponseRegister
import com.company.skinnie.data.service.ApiConfig.provideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    fun register(body: PayloadRegister): LiveData<ResponseRegister?>{
        val mutableLiveData = MutableLiveData<ResponseRegister?>()

        provideRetrofit().register(body).enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>){
                if (response.isSuccessful){
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable){
                mutableLiveData.value = null
            }
        })
        return mutableLiveData
    }
}