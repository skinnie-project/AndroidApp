package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class PayloadGoogle(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("nickname")
	val nickname: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
