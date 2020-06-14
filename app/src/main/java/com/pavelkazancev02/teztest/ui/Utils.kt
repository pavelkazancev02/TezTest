package com.pavelkazancev02.teztest.ui

import java.net.Inet4Address

fun formatAddress(address: String): String {
    if (address.length > 30)
        return address.substring(0, 7) + "..." + address.reversed().substring(0, 7).reversed()
    else
        return address
}

fun formatTime(time: String): String {
     return time.substring(0,10)+", "+time.substring(11,19)
}