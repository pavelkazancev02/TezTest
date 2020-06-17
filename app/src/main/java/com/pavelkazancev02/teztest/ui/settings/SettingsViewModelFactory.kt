package com.pavelkazancev02.teztest.ui.settings

import com.pavelkazancev02.teztest.ui.network_info.NetworkInfoViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import com.pavelkazancev02.teztest.ui.subscriptions.SubscriptionsViewModel

class SettingsViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}