package com.pavelkazancev02.teztest.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pavelkazancev02.teztest.data.Variables
import com.pavelkazancev02.teztest.data.value_object.account.Account
import com.pavelkazancev02.teztest.data.value_object.account_op.AccountOps
import com.pavelkazancev02.teztest.data.value_object.block.Block
import com.pavelkazancev02.teztest.data.value_object.explorer_tip.ExplorerTip
import com.pavelkazancev02.teztest.data.value_object.market_tickers.MarketTickersItem
import com.pavelkazancev02.teztest.data.value_object.transaction.Transaction
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Variables.getBaseUrl())
    .build()

fun reBuildRetrofit(){
    retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Variables.getBaseUrl())
        .build()
}

interface TezosApiService {

    @GET ("explorer/tip")
    fun getExplorerTip(): Call<ExplorerTip>

    @GET ("markets/tickers")
    fun getMarketTickers(): Call<List<MarketTickersItem>>

    @GET ("explorer/account/{account_address}")
    fun getAccountData(@Path("account_address") address: String): Call<Account>

    @GET ("explorer/account/{account_address}/op?order=desc")
    fun getAccountOperations(@Path("account_address") address: String): Call<AccountOps>

    @GET ("series/kraken/XTZ_USD/ohlcv?start_date=now-7d&collapse=7d")
    fun getWeekPrice(): Call<List<List<String>>>

    @GET ("explorer/op/{transaction_hash}")
    fun getTransactionData(@Path("transaction_hash") hash: String): Call<List<Transaction>>

    @GET ("explorer/block/{block_hash}")
    fun getBlockData(@Path("block_hash") hash: String): Call<Block>

}

object TezosApi {

    fun getRetrofitService(): TezosApiService{
        return retrofit.create(TezosApiService::class.java)
    }

}