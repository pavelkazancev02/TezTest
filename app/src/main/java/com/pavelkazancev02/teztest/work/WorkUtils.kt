package com.pavelkazancev02.teztest.work

import android.util.Log
import com.pavelkazancev02.teztest.data.api.Responder
import com.pavelkazancev02.teztest.data.api.TezosApi
import com.pavelkazancev02.teztest.data.value_object.account_op.AccountOps
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getLastAccountOperation(address: String): String? {
    Responder.isLastOperationResponded = 0
    var operation: String?
    operation=""
    TezosApi.getRetrofitService().getAccountOperations(address).enqueue(object:
        Callback<AccountOps> {
        override fun onFailure(call: Call<AccountOps>, t: Throwable) {
            //TODO
        }

        override fun onResponse(call: Call<AccountOps>, response: Response<AccountOps>) {
            operation = response.body()?.ops?.get(0)?.hash
            Responder.isLastOperationResponded = 1
        }

    })
    //Add Timer
    while (Responder.isLastOperationResponded==0)
        Thread.sleep(1000)

    return operation
}
