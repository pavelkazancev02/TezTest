package com.pavelkazancev02.teztest.work

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.pavelkazancev02.teztest.R

//class RefreshDataWorker(appContext: Context, params: WorkerParameters):
//    CoroutineWorker(appContext, params) {
//    override suspend fun doWork():ListenableWorker.Result {
//        var builder = NotificationCompat.Builder(applicationContext, "1")
//            .setSmallIcon(R.drawable.subscriptions_24dp)
//            .setContentTitle("MyNotification")
//            .setContentText("Yeahh")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//    }
//}