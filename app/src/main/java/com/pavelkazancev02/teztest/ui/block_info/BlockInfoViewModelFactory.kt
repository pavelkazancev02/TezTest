package com.pavelkazancev02.teztest.ui.block_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlockInfoViewModelFactory(private val searchFieldData: String,
                                private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlockInfoViewModel::class.java)) {
            return BlockInfoViewModel(searchFieldData,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}