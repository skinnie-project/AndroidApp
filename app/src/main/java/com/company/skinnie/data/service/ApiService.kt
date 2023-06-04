package com.company.skinnie.data.service

import com.company.skinnie.data.response.PayloadLogin
import com.company.skinnie.data.response.PayloadRegister
import com.company.skinnie.data.response.ResponseLogin
import com.company.skinnie.data.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(
        @Body body: PayloadLogin
    ): Call<ResponseLogin>

    @POST("register")
    fun register(
        @Body body: PayloadRegister
    ): Call<ResponseRegister>
}