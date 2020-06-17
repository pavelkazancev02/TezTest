package com.pavelkazancev02.teztest.data

object Variables {
    var NETWORK_TYPE: String = "Mainnet"

    fun getBaseUrl(): String {
        if (NETWORK_TYPE == "Mainnet")
            return "https://api.tzstats.com"
        else
            return "https://api.carthagenet.tzstats.com"
    }
}