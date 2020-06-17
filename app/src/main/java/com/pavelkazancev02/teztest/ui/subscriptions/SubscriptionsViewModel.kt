package com.pavelkazancev02.teztest.ui.subscriptions

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.pavelkazancev02.teztest.data.Variables
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.room_db.SubscribedAccount
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import kotlinx.coroutines.*

class SubscriptionsViewModel(
    val database: SubscriptionsDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _navigateToAccountInfo = MutableLiveData<String>()
    val navigateToAccountInfo: LiveData<String>
        get() = _navigateToAccountInfo

    fun doneNavigating(){
        _navigateToAccountInfo.value = null
    }

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val allAccounts = database.getAllAccounts(NETWORK_TYPE)

    val networkType = Variables.NETWORK_TYPE

    fun onInfoClicked(address: String) {
        _navigateToAccountInfo.value = address
    }

    fun onUnsubscribeClicked(address: String) {
        unsubscribe(address)
    }

    fun unsubscribe(address: String){
        uiScope.launch {
            removeAccountFromDatabase(address)
        }
    }

    private suspend fun removeAccountFromDatabase(address: String) {
        uiScope.launch {
            remove(address)
        }
    }

    private suspend fun remove(address: String) {
        withContext(Dispatchers.IO) {
            database.removeByAddress(address)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}


//DB Operations that can be reusable

//    fun onClear() {
//        uiScope.launch {
//            clear()
//            _lastAccount.value = null
//        }
//    }

//    suspend fun clear() {
//        withContext(Dispatchers.IO) {
//            database.clear()
//        }
//    }


//    private suspend fun insert(account: SubscribedAccount) {
//        withContext(Dispatchers.IO) {
//            database.insert(account)
//        }
//    }
//
//    private suspend fun update(account: SubscribedAccount) {
//        withContext(Dispatchers.IO) {
//            database.update(account)
//        }
//    }


