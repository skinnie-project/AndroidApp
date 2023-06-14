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

    @POST("login/google")
    fun google(
        @Body body: PayloadGoogle
    ): Call<ResponseGoogle>

    @POST("forgot")
    fun forgot(
        @Body body: PayloadForgotPassword
    ): Call<ResponseForgotPassword>

    @Multipart
    @POST("predict")
    fun postImage(
        @Part file: MultipartBody.Part,
        @Part("filename") filename: RequestBody
    ): Call<ResponseScan>

    @GET("predict/recommend")
    fun getRecommendation(
        @Query("predicted") predicted: String,
    ): Call<List<ResponsePopularItem>>

    @GET("data/detail")
    fun getDetail(
        @Query("id") id: Int,
    ): Call<List<ResponsePopularItem>>

    @GET("predict/filter")
    fun getFilter(
        @Query("ingredients") ingredients: String,
        @Query("subcategory") subcategory: String,
        @Query("predicted") predicted: String,
    ): Call<List<ResponseFilter.ResponseFilterItem>>

    @GET("article")
    fun getArticle(
    ): Call<List<ResponseArticleItem>>

    @GET("data/popular")
    fun getPopular(
    ): Call<List<ResponsePopularItem>>

    @GET("data/search")
    fun getProduct(
        @Query("product") query: String
    ): Call<List<ResponsePopularItem>>
}