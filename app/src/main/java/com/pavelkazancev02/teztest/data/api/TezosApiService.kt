package com.pavelkazancev02.teztest.data.api

import com.pavelkazancev02.teztest.data.value_object.account.Account
import com.pavelkazancev02.teztest.data.value_object.account_op.AccountOps
import com.pavelkazancev02.teztest.data.value_object.explorer_tip.ExplorerTip
import com.pavelkazancev02.teztest.data.value_object.market_tickers.MarketTickersItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

private const val BASE_URL = "https://api.tzstats.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TezosApiService {
    @GET ("explorer/tip")
    fun getExplorerTip(): Call<ExplorerTip>

    @GET ("markets/tickers")
    fun getMarketTickers(): Call<List<MarketTickersItem>>

    @GET ("explorer/account/{account_address}")
    fun getAccountData(@Path("account_address") address: String): Call<Account>

    @GET ("explorer/account/{account_address}/op")
    fun getAccountOperations(@Path("account_address") address: String): Call<AccountOps>

}

object TezosApi {
    val retrofitService : TezosApiService by lazy {
        retrofit.create(TezosApiService::class.java)
    }
}