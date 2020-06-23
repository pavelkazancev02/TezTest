package com.pavelkazancev02.teztest.ui.block_info

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pavelkazancev02.teztest.data.api.TezosApi
import com.pavelkazancev02.teztest.data.value_object.block.Block
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockInfoViewModel(private val searchFieldData: String,
                         application: Application
) : AndroidViewModel(application) {


    val retrofitService = TezosApi.getRetrofitService()

    private val _blockResponse = MutableLiveData<Block>()
    val blockResponse : LiveData<Block>
        get() = _blockResponse

    var blockHash = String()

    init {
        if (checkAddressExistance()){
            displayData()
        }
        else displayError()
    }

    private fun displayError() {
        blockHash = "Error"
    }

    private fun displayData() {
        blockHash = searchFieldData
        getBlockResponse(blockHash)
    }

    private fun checkAddressExistance(): Boolean {
        return true
    }

    private fun getBlockResponse(hash: String) {
        retrofitService.getBlockData(hash).enqueue(object: Callback<Block> {
            override fun onFailure(call: Call<Block>, t: Throwable) {
                Log.i("test9", t.toString())
            }

            override fun onResponse(call: Call<Block>, response: Response<Block>) {
                _blockResponse.value = response.body()
            }
        })
    }
}