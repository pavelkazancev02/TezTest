package com.pavelkazancev02.teztest.ui.network_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import com.pavelkazancev02.teztest.ui.subscriptions.SubscriptionsViewModel

class NetworkInfoViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetworkInfoViewModel::class.java)) {
            return NetworkInfoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}