package com.pavelkazancev02.teztest.ui.settings

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pavelkazancev02.teztest.data.Variables
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.api.reBuildRetrofit
import com.pavelkazancev02.teztest.data.api.retrofit
import com.pavelkazancev02.teztest.databinding.FragmentSettingsBinding


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

        val application = requireNotNull(this.activity).application

        val settingsViewModelFactory = SettingsViewModelFactory(application)

        val settingsViewModel = ViewModelProviders.of(this, settingsViewModelFactory).get(SettingsViewModel::class.java)

        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = settingsViewModel

        settingsViewModel.networkTypeItemPosition.observe(this, Observer {
            changeNetwork ->
            changeNetwork?.let {
                if (settingsViewModel.networkTypeItemPosition.value == 1) {
                    NETWORK_TYPE = "Carthagenet"
                    reBuildRetrofit()
                    Log.i("testurl", retrofit.baseUrl().toString())
                }
                else {
                    NETWORK_TYPE = "Mainnet"
                    reBuildRetrofit()
                    Log.i("testurl", retrofit.baseUrl().toString())
                }
            }
        })

//        settingsViewModel.observe(this, Observer {
//                navEvent ->
//            navEvent?.let {
//                this.findNavController().navigate(
//                    NetworkFragmentDirections.actionNetworkInfoFragmentToAccountInfoFragment(navEvent))
//                networkInfoViewModel.doneNavigating()
//            }
//        })



//        notificationManager =  activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        binding.notificationTest.setOnClickListener {
//
//            val intent = Intent(activity, UserActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(activity,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
//                notificationChannel.enableLights(true)
//                notificationChannel.lightColor = Color.GREEN
//                notificationChannel.enableVibration(false)
//                notificationManager.createNotificationChannel(notificationChannel)
//
//                builder = Notification.Builder(activity, channelId)
//                    .setContentTitle("tz1d75oB6T4zUMexzkr5WscGktZ1Nss1JrT7")
//                    .setContentText("New transaction")
//                    .setSmallIcon(R.drawable.logo)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo))
//                    .setContentIntent(pendingIntent)
//
//
//            }
//            else{
//
//                builder = Notification.Builder(activity)
//                    .setContentTitle("New transaction")
//                    .setContentText("Yeah")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
//                    .setContentIntent(pendingIntent)
//
//            }
//            notificationManager.notify(1234, builder.build())
//        }



        return binding.root
    }
}
