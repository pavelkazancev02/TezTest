package com.pavelkazancev02.teztest.ui.settings

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE

class SettingsViewModel(
    application: Application
) : AndroidViewModel(application) {
    var entries = arrayOf("Mainnet","Carthagenet")

    val networkTypeItemPosition = MutableLiveData<Int>()

    init {
        if (NETWORK_TYPE == "Carthagenet")
            networkTypeItemPosition.value = 1
        else
            networkTypeItemPosition.value = 0
    }
}