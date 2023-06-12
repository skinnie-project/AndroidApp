package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseGoogle(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
