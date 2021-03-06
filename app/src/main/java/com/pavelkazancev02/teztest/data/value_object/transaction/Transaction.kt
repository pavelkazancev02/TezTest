package com.pavelkazancev02.teztest.data.value_object.transaction


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
    val fee: Double,
    val hash: String,
    val `receiver`: String,
    val sender: String,
    val time: String,
    val volume: Double
)