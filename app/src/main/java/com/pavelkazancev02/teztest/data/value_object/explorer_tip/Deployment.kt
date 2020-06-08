package com.pavelkazancev02.teztest.data.value_object.explorer_tip


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Deployment(
    val deployment: Int,
    @Json(name = "end_height")
    val endHeight: Int,
    val protocol: String,
    @Json(name = "start_height")
    val startHeight: Int,
    val version: Int
)