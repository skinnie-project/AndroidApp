package com.company.skinnie.data.response

import com.google.gson.annotations.SerializedName

data class ResponseScan(

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
