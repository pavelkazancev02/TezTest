package com.pavelkazancev02.teztest.ui.transaction_info

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pavelkazancev02.teztest.data.api.TezosApi
import com.pavelkazancev02.teztest.data.value_object.transaction.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionInfoViewModel(private val searchFieldData: String,
    application: Application
) : AndroidViewModel(application) {


    val retrofitService = TezosApi.getRetrofitService()

    private val _transactionResponse = MutableLiveData<List<Transaction>>()
    val transactionResponse : LiveData<List<Transaction>>
        get() = _transactionResponse

    var transactionHash = String()

    init {
        if (checkAddressExistance()){
            displayData()
        }
        else displayError()
    }

    private fun displayError() {
        transactionHash = "Error"
    }

    private fun displayData() {
        transactionHash = searchFieldData
        getTransactionResponse(transactionHash)
    }

    private fun checkAddressExistance(): Boolean {
        return true
    }

    private fun getTransactionResponse(hash: String) {
        retrofitService.getTransactionData(hash).enqueue(object: Callback<List<Transaction>> {
            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                Log.i("test8", t.toString())
            }

            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                _transactionResponse.value = response.body()
            }
        })
    }
}