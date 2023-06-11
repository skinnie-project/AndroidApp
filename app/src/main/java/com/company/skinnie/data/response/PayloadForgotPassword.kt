package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class PayloadForgotPassword(

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("new_password")
	val newPassword: String,
)
