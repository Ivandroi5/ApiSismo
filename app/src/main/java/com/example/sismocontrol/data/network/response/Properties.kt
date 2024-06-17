package com.example.sismocontrol.data.network.response

import com.squareup.moshi.Json

class Properties(
    @Json(name = "mag")
    val magnitude: Double,
    val place:String,
    val time: Long
) {
}