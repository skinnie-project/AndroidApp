package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseRecommend(

    @field:SerializedName("ResponseRecommend")
    val responseRecommend: List<ResponseRecommendItem?>? = null
) {
    data class ResponseRecommendItem(

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
        val price: String? = null,

        @field:SerializedName("ingredients")
        val ingredients: String? = null,

        @field:SerializedName("reviewed")
        val reviewed: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("recom")
        val recom: String? = null,

        @field:SerializedName("subcategory")
        val subcategory: String? = null,

        @field:SerializedName("brand")
        val brand: String? = null
    )
}


