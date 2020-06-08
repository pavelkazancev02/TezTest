package com.pavelkazancev02.teztest.ui.subscriptions

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao

class SubscriptionsViewModelFactory(
    private val dataSource: SubscriptionsDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriptionsViewModel::class.java)) {
            return SubscriptionsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
