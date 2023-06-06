package com.company.skinnie.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.skinnie.data.response.ResponseScan
import com.company.skinnie.data.service.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PreviewImageViewModel: ViewModel() {
    fun uploadImage(photo: File, filename: String): LiveData<ResponseScan?> {
        val mutableLiveData = MutableLiveData<ResponseScan?>()

        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            photo.name,
            photo.asRequestBody("image/jpeg".toMediaTypeOrNull()),
        )

        ApiConfig.provideRetrofit().postImage(imageMultipart, filename.toRequestBody())
            .enqueue(object : Callback<ResponseScan> {
                override fun onResponse(
                    call: Call<ResponseScan>,
                    response: Response<ResponseScan>
                ) {
                    if (response.isSuccessful) {
                        mutableLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ResponseScan>, t: Throwable) {
                    mutableLiveData.value = null
                }
            })
        return mutableLiveData
    }
}