package com.pavelkazancev02.teztest.ui.settings

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.databinding.FragmentNetworkBinding
import com.pavelkazancev02.teztest.databinding.FragmentSettingsBinding
import com.pavelkazancev02.teztest.ui.activities.UserActivity


class SettingsFragment : Fragment() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.pavelkazancev02.teztest.ui.settings"
    private val description = "Transactions notification"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSettingsBinding.inflate(inflater)

        notificationManager =  activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.notificationTest.setOnClickListener {

            val intent = Intent(activity, UserActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(activity,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(activity, channelId)
                    .setContentTitle("tz1d75oB6T4zUMexzkr5WscGktZ1Nss1JrT7")
                    .setContentText("New transaction")
                    .setSmallIcon(R.drawable.logo)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo))
                    .setContentIntent(pendingIntent)


            }
            else{

                builder = Notification.Builder(activity)
                    .setContentTitle("New transaction")
                    .setContentText("Yeah")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)

            }
            notificationManager.notify(1234, builder.build())
        }

        return binding.root
    }
}
