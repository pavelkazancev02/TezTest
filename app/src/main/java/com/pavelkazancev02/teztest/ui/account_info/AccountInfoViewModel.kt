package com.pavelkazancev02.teztest.ui.account_info

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.NetworkType
import com.pavelkazancev02.teztest.data.Variables
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.api.TezosApi
import com.pavelkazancev02.teztest.data.room_db.SubscribedAccount
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import com.pavelkazancev02.teztest.data.value_object.account.Account
import com.pavelkazancev02.teztest.data.value_object.account_op.AccountOps
import com.pavelkazancev02.teztest.data.value_object.market_tickers.MarketTickersItem
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInfoViewModel(private val searchFieldData: String = "", val database: SubscriptionsDatabaseDao, application: Application)  : ViewModel() {


    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val retrofitService = TezosApi.getRetrofitService()

    var accountType = "User Account"

    private val  _lastAccount = MutableLiveData<SubscribedAccount?>()
    val lastAccount: LiveData<SubscribedAccount?>
        get() = _lastAccount

    //Information from explorer/market/tickers
    private val _marketTickersResponse = MutableLiveData<List<MarketTickersItem>>()
    val marketTickersResponse : LiveData<List<MarketTickersItem>>
        get() = _marketTickersResponse

    //Information from account/address
    private val _accountResponse = MutableLiveData<Account>()
    val accountResponse : LiveData<Account>
        get() = _accountResponse

    //Information from account/address/op
    private val _accountOperations = MutableLiveData<AccountOps>()
    val accountOperations : LiveData<AccountOps>
        get() = _accountOperations


    var accountAddress = String()


    private val _subscribed = MutableLiveData<Boolean>()
    val subscribed: LiveData<Boolean>
            get() = _subscribed


    init {

       if (checkAddressExistance()){
           if(searchFieldData[0]=='K')
               accountType = "Smart Contract"
           displayData()
           checkSubscription()
           updateNewTransactionsCount(searchFieldData)
       }
        else displayError()
    }

    private fun updateNewTransactionsCount(address: String) {
        uiScope.launch {
            updateNewTransactionsInDatabase(address)
        }
    }

    private suspend fun updateNewTransactionsInDatabase(address: String) {
        withContext(Dispatchers.IO) {
            database.updateNewTransactions(address,0)
        }
    }

    private fun displayError() {
        accountAddress = "Error"
    }

    private fun displayData() {
        accountAddress = searchFieldData.toString()
        getAccountResponse(accountAddress)
        getAccountOperations(accountAddress)
        getMarketTickersResponse()
    }

    private fun checkAddressExistance(): Boolean {
        return true
    }

    private fun getAccountResponse(address: String) {
        retrofitService.getAccountData(address).enqueue(object: Callback<Account> {
            override fun onFailure(call: Call<Account>, t: Throwable) {
                //TODO
                Log.i("test1", t.toString())
            }

            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                _accountResponse.value = response.body()
            }
        })
    }

    private fun getAccountOperations(address: String) {
        retrofitService.getAccountOperations(address).enqueue(object: Callback<AccountOps> {
            override fun onFailure(call: Call<AccountOps>, t: Throwable) {
                //TODO
            }

            override fun onResponse(call: Call<AccountOps>, response: Response<AccountOps>) {
                _accountOperations.value = response.body()
            }

        })
    }

    private fun getMarketTickersResponse() {
        retrofitService.getMarketTickers().enqueue(object: Callback<List<MarketTickersItem>>{
            override fun onFailure(call: Call<List<MarketTickersItem>>, t: Throwable) {
                //TODO
            }

            override fun onResponse(call: Call<List<MarketTickersItem>>, response: Response<List<MarketTickersItem>>) {
                _marketTickersResponse.value = response.body()
            }
        })
    }

    fun onSubscribeUnsubscribe(){
        if (_subscribed.value == false)
            subscribe()
        else
            unsubscribe()
    }

    //Checks whether account present in subscriptions Database
    fun checkSubscription(){
        uiScope.launch {
            _subscribed.value = getAccountFromDatabase(searchFieldData) != null
        }
    }
    //Subscribe to account
    fun subscribe(){
        uiScope.launch {
            addAccountToDatabase(searchFieldData)
            _subscribed.value = true
        }
    }

    //Unsubscribe from account
    fun unsubscribe(){
        uiScope.launch {
            removeAccountFromDatabase(searchFieldData)
            _subscribed.value = false
        }
    }


    private suspend fun getAccountFromDatabase(address: String):  SubscribedAccount? {
        return withContext(Dispatchers.IO) {
            var account = database.getByAddress(address)
            account
        }
    }

    private suspend fun addAccountToDatabase(address: String) {
        uiScope.launch {
            val newAccount = SubscribedAccount(accountAddress = address, networkType = NETWORK_TYPE, lastTransaction = accountOperations.value?.ops?.get(0)?.hash)
            insert(newAccount)
        }
    }

    private suspend fun removeAccountFromDatabase(address: String) {
        uiScope.launch {
            remove(address)
        }
    }

    //Database interactions

    private suspend fun insert(account: SubscribedAccount) {
        withContext(Dispatchers.IO) {
            database.insert(account)
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
