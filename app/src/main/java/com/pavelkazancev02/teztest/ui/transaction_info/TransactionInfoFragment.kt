package com.pavelkazancev02.teztest.ui.transaction_info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabase
import com.pavelkazancev02.teztest.databinding.FragmentAccountInfoBinding
import com.pavelkazancev02.teztest.databinding.FragmentTransactionInfoBinding
import com.pavelkazancev02.teztest.ui.account_info.AccountInfoFragmentArgs
import com.pavelkazancev02.teztest.ui.account_info.AccountInfoViewModel
import com.pavelkazancev02.teztest.ui.account_info.AccountInfoViewModelFactory
import com.pavelkazancev02.teztest.ui.account_info.recycler_view.OperationsAdapter

class TransactionInfoFragment : Fragment() {

    private val viewModel: TransactionInfoViewModel by lazy {
        ViewModelProviders.of(this).get(TransactionInfoViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTransactionInfoBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val arguments = TransactionInfoFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = TransactionInfoViewModelFactory(arguments.transactionHash, application)

        val transactionInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(TransactionInfoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = transactionInfoViewModel

        return binding.root
    }

}