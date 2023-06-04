package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
