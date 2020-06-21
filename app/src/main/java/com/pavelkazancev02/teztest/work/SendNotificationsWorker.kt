package com.pavelkazancev02.teztest.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.*
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.api.Responder
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabase
import com.pavelkazancev02.teztest.ui.activities.UserActivity
import com.pavelkazancev02.teztest.ui.subscriptions.SubscriptionsFragment


class SendNotificationsWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    private val channelId = "com.pavelkazancev02.teztest.work"
    private val description = "Transactions notification"
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder

    companion object {
        const val WORK_NAME = "SendNotificationsWorker"
    }

    override suspend fun doWork(): Result {
        try {
            while (Responder.isLoggedIn==0){
                Thread.sleep(5000)
            }
           Thread.sleep(15000)
           doTheActualProcessingWork()
        } catch (e: Exception) {
            Log.d("MyWorker", "exception in doWork ${e.message}")
        }
        return Result.success()
    }

    fun doTheActualProcessingWork() {
        while (Responder.isLoggedIn==0){
            Thread.sleep(5000)
        }

        val isNotify = checkForNewTransactions()

        if (isNotify)
            notifyAboutTransactions()


        val workRequest = OneTimeWorkRequestBuilder<SendNotificationsWorker>().build()

        WorkManager.getInstance().enqueueUniqueWork(
            SendNotificationsWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

//Checking new transactions from the network

    private fun checkForNewTransactions(): Boolean {
        var isNotify = false

        val dataSource = SubscriptionsDatabase.getInstance(applicationContext).subscriptionsDatabaseDao

        val accounts = dataSource.getAllAccountsList(NETWORK_TYPE)

        accounts?.forEach {
            val lastTransactionFromApi =  getLastAccountOperation(it?.accountAddress.toString())
            Log.i("test14", lastTransactionFromApi)
            if (it?.lastTransaction != lastTransactionFromApi){
                isNotify = true
                val address = it?.accountAddress.toString()
                dataSource.updateLastTransaction(address, lastTransactionFromApi)
                val newTransactions = dataSource.getNewTransactions(address)
                dataSource.updateNewTransactions(address, newTransactions+1)
            }
        }

        return isNotify
    }






    //Sending notifications
    private fun notifyAboutTransactions() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setComponentName(UserActivity::class.java)
            .setGraph(R.navigation.nav_graph_network)
            .setDestination(R.id.subscriptionsFragment)
            .createPendingIntent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(applicationContext, channelId)
                .setContentTitle("You have new transactions")
                .setContentText("Tap to check!")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.logo
                    )
                )
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        } else {

            builder = Notification.Builder(applicationContext)
                .setContentTitle("You have new transactions")
                .setContentText("Tap to check!")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.logo
                    )
                )
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        }

        notificationManager.notify(1234, builder.build())
    }
}

