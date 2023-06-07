package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseScan(

    @field:SerializedName("key_ingredients_1")
    val keyIngredients1: String? = null,

    @field:SerializedName("key_ingredients_2")
    val keyIngredients2: String? = null,

    @field:SerializedName("key_ingredients_3")
    val keyIngredients3: String? = null,

    @field:SerializedName("key_ingredients_4")
    val keyIngredients4: String? = null,

    @field:SerializedName("prediction_rate")
    val predictionRate: PredictionRate? = null,

    @field:SerializedName("predicted")
    val predicted: String? = null
) {

    data class PredictionRate(

        @field:SerializedName("Kering")
        val kering: Any? = null,

        @field:SerializedName("Normal")
        val normal: Any? = null,

        @field:SerializedName("Berminyak")
        val berminyak: Any? = null
    )

}
