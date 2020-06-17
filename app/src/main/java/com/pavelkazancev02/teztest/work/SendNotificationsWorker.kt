package com.pavelkazancev02.teztest.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.work.*
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabase


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
            Log.d("MyWorker", "Sleeping")
            //Do Your task here
        //    Thread.sleep(15000)
       //     doTheActualProcessingWork()
        } catch (e: Exception) {
            Log.d("MyWorker", "exception in doWork ${e.message}")
        }
        return Result.success()
    }

    fun doTheActualProcessingWork() {
        Log.d("MyWorker", "Processing work...");

        val address = checkForNewTransactions()

        if (address.length>1)
            notifyAboutTransactions(address)


        val workRequest = OneTimeWorkRequestBuilder<SendNotificationsWorker>().build()

        WorkManager.getInstance().enqueueUniqueWork(
            SendNotificationsWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }

//Checking new transactions from the network

    private fun checkForNewTransactions(): String {
        var address = ""

        val dataSource = SubscriptionsDatabase.getInstance(applicationContext).subscriptionsDatabaseDao

        val subscribedAccounts = dataSource.getAllAccounts(NETWORK_TYPE)



        address = "tz1d75oB6T4zUMexzkr5WscGktZ1Nss1JrT7"

        return address
    }


//Sending notifications
    private fun notifyAboutTransactions(address: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(applicationContext, channelId)
                .setContentTitle(address)
                .setContentText("New transaction")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.logo
                    )
                )
        } else {

            builder = Notification.Builder(applicationContext)
                .setContentTitle("New transaction")
                .setContentText("Yeah")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.logo
                    )
                )

        }
        notificationManager.notify(1234, builder.build())
    }
}

