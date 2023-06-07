package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseFilter(

	@field:SerializedName("ResponseFilter")
	val responseFilter: List<ResponseFilterItem?>? = null
){
	data class ResponseFilterItem(

		@field:SerializedName("suitable_for")
		val suitableFor: String? = null,

		@field:SerializedName("description")
		val description: String? = null,

		@field:SerializedName("how_to_use")
		val howToUse: String? = null,

		@field:SerializedName("product_name")
		val productName: String? = null,

		@field:SerializedName("url_new")
		val urlNew: String? = null,

		@field:SerializedName("rate")
		val rate: String? = null,

		@field:SerializedName("price")
		val price: Int? = null,

		@field:SerializedName("ingredients")
		val ingredients: String? = null,

		@field:SerializedName("reviewed")
		val reviewed: Int? = null,

		@field:SerializedName("id")
		val id: Int? = null,

		@field:SerializedName("recom")
		val recom: Int? = null,

		@field:SerializedName("subcategory")
		val subcategory: String? = null,

		@field:SerializedName("brand")
		val brand: String? = null
	)

}

