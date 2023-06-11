package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class PayloadLogin(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("password")
	val password: String,
)
