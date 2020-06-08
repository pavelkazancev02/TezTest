package com.pavelkazancev02.teztest.ui.account_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import com.pavelkazancev02.teztest.ui.network_info.NetworkInfoViewModel

class AccountInfoViewModelFactory( private val searchFieldData: String,
                                   private val dataSource: SubscriptionsDatabaseDao,
                                   private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountInfoViewModel::class.java)) {
            return AccountInfoViewModel(searchFieldData, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}