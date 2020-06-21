package com.pavelkazancev02.teztest.ui.network_info

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.api.TezosApi
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

    val retrofitService = TezosApi.getRetrofitService()

    var accountAddress = String()


    var graphUrl = ""

    var graphVisibility = false

    val networkType = NETWORK_TYPE

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

    private val _weekPriceResponse = MutableLiveData<List<List<String>>>()
    val weekPriceResponse : LiveData<List<List<String>>>
        get() = _weekPriceResponse


    init {
        accountAddress = ""
        getExplorerTipResponse()
        getMarketTickersResponse()
        getWeekPrice()
        if (NETWORK_TYPE=="Mainnet") {
            graphUrl = "https://s2.coinmarketcap.com/generated/sparklines/web/7d/usd/2011.png"
            graphVisibility = true
        }
    }

    private fun getWeekPrice() {
        retrofitService.getWeekPrice().enqueue(object: Callback<List<List<String>>>{
            override fun onFailure( call: Call<List<List<String>>>, t: Throwable) {
                //TODO
            }

            override fun onResponse(
                call: Call<List<List<String>>>,
                response: Response<List<List<String>>>
            ) {
                _weekPriceResponse.value = response.body()
            }
        })
    }


    private fun getExplorerTipResponse() {

        retrofitService.getExplorerTip().enqueue(object: Callback<ExplorerTip>{
            override fun onFailure(call: Call<ExplorerTip>, t: Throwable) {
                //TODO
            }

            override fun onResponse(call: Call<ExplorerTip>, response: Response<ExplorerTip>) {
                _explorerTipResponse.value = response.body()
            }
        })
    }

    private fun getMarketTickersResponse() {
        retrofitService.getMarketTickers().enqueue(object: Callback<List<MarketTickersItem>>{
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