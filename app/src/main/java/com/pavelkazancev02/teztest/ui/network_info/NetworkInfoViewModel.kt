package com.pavelkazancev02.teztest.ui.network_info

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.location.Address
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.pavelkazancev02.teztest.data.api.TezosApi
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabaseDao
import com.pavelkazancev02.teztest.data.value_object.account.Account
import com.pavelkazancev02.teztest.data.value_object.explorer_tip.ExplorerTip
import com.pavelkazancev02.teztest.data.value_object.market_tickers.MarketTickersItem
//import com.pavelkazancev02.teztest.data.api.TezosApiClient
//import com.pavelkazancev02.teztest.data.api.TezosApiInterface
//import com.pavelkazancev02.teztest.data.repository.ExplorerTipNetworkDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class NetworkInfoViewModel(
    application: Application
) : AndroidViewModel(application) {

    val decimalFormat = DecimalFormat("#,###")

    var accountAddress = String()

    private val _navigateToAccountInfo = MutableLiveData<String>()
    val navigateToAccountInfo: LiveData<String>
            get() = _navigateToAccountInfo

    fun doneNavigating(){
        _navigateToAccountInfo.value = null
    }


    private val _marketTickersResponse = MutableLiveData<List<MarketTickersItem>>()
    val marketTickersResponse : LiveData<List<MarketTickersItem>>
        get() = _marketTickersResponse

    private val _explorerTipResponse = MutableLiveData<ExplorerTip>()
    val explorerTipResponse : LiveData<ExplorerTip>
        get() = _explorerTipResponse


    init {
        accountAddress = ""
        getExplorerTipResponse()
        getMarketTickersResponse()
    }



    private fun getExplorerTipResponse() {
        TezosApi.retrofitService.getExplorerTip().enqueue(object: Callback<ExplorerTip>{
            override fun onFailure(call: Call<ExplorerTip>, t: Throwable) {
                //TODO
            }

            override fun onResponse(call: Call<ExplorerTip>, response: Response<ExplorerTip>) {
                _explorerTipResponse.value = response.body()
            }
        })
    }

    private fun getMarketTickersResponse() {
        TezosApi.retrofitService.getMarketTickers().enqueue(object: Callback<List<MarketTickersItem>>{
            override fun onFailure(call: Call<List<MarketTickersItem>>, t: Throwable) {
                //TODO
                Log.i("failure", t.message)
            }

            override fun onResponse(call: Call<List<MarketTickersItem>>, response: Response<List<MarketTickersItem>>) {
                _marketTickersResponse.value = response.body()
                Log.i("failure", _marketTickersResponse.value.toString())
            }
        })
    }

    fun onSearch(){
        if (accountAddress.length == 36)
        _navigateToAccountInfo.value = accountAddress
    }

}