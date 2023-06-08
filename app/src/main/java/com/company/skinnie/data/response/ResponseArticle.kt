package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseArticle(

	@field:SerializedName("ResponseArticle")
	val responseArticle: List<ResponseArticleItem?>? = null
)

data class ResponseArticleItem(

	@field:SerializedName("sumber")
	val sumber: String? = null,

	@field:SerializedName("ditinjau")
	val ditinjau: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null
)
