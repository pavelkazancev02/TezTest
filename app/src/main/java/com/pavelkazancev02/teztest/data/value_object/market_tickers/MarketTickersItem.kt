package com.pavelkazancev02.teztest.data.value_object.market_tickers


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketTickersItem(
    val base: String,
    val change: Double,
    val exchange: String,
    val high: Double,
    val last: Double,
    val low: Double,
    @Json(name = "n_trades")
    val nTrades: Int,
    val `open`: Double,
    val pair: String,
    val quote: String,
    val timestamp: String,
    @Json(name = "volume_base")
    val volumeBase: Double,
    @Json(name = "volume_quote")
    val volumeQuote: Double,
    val vwap: Double
)