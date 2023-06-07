package com.company.skinnie.data.service

import com.company.skinnie.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    @Multipart
    @POST("predict")
    fun postImage(
        @Part file: MultipartBody.Part,
        @Part("filename") filename: RequestBody
    ): Call<ResponseScan>

    @GET("predict/recommend")
    fun getRecommendation(
        @Query("predicted") predicted: String,
    ): Call<List<ResponseRecommend.ResponseRecommendItem>>

    @GET("data/detail")
    fun getDetail(
        @Query("id") id: Int,
    ): Call<List<ResponseDetail>>

    @GET("predict/filter")
    fun getFilter(
        @Query("ingredients") ingredients: String,
        @Query("subcategory") subcategory: String,
        @Query("predicted") predicted: String,
    ): Call<List<ResponseFilter.ResponseFilterItem>>
}