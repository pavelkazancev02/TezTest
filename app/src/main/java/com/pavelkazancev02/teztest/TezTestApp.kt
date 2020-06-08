package com.pavelkazancev02.teztest

import android.app.Application
import android.util.Log

class TezTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("testOn", "appCreated")
     //   Thread.sleep(10000)
    }
}