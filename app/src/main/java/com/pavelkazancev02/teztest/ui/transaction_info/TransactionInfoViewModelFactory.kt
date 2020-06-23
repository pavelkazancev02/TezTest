package com.pavelkazancev02.teztest.ui.transaction_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavelkazancev02.teztest.ui.settings.SettingsViewModel

class TransactionInfoViewModelFactory(private val searchFieldData: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionInfoViewModel::class.java)) {
            return TransactionInfoViewModel(searchFieldData,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}