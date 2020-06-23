package com.pavelkazancev02.teztest.data.value_object.block


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Block(
    val baker: String,
    val fee: Double,
    val hash: String,
    @Json(name = "n_ops")
    val nOps: Int,
    val time: String,
    val volume: Double
)