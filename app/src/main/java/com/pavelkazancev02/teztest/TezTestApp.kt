package com.pavelkazancev02.teztest

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.work.*
import com.pavelkazancev02.teztest.work.SendNotificationsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TezTestApp: Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() = applicationScope.launch {
        setupRecurringWork()
    }

    private fun setupRecurringWork() {
        val workRequest = OneTimeWorkRequestBuilder<SendNotificationsWorker>().build()

        WorkManager.getInstance().enqueueUniqueWork(
            SendNotificationsWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )

    }

}