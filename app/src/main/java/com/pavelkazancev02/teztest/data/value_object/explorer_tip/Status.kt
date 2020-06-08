package com.pavelkazancev02.teztest.data.value_object.explorer_tip


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    val blocks: Int,
    val indexed: Int,
    val progress: Int,
    val status: String
)