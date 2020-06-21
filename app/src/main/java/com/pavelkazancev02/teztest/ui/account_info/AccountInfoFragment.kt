package com.pavelkazancev02.teztest.ui.account_info

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabase
import com.pavelkazancev02.teztest.databinding.FragmentAccountInfoBinding
import com.pavelkazancev02.teztest.ui.account_info.recycler_view.OperationsAdapter


class AccountInfoFragment : Fragment() {

    private val viewModel: AccountInfoViewModel by lazy {
        ViewModelProviders.of(this).get(AccountInfoViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAccountInfoBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dataSource = SubscriptionsDatabase.getInstance(application).subscriptionsDatabaseDao
        val arguments = AccountInfoFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = AccountInfoViewModelFactory(arguments.accountAddress, dataSource, application)

        val accountInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(AccountInfoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = accountInfoViewModel

        //RecyclerView Adapter
        val adapter = OperationsAdapter()
        binding.operationsRecyclerView.adapter = adapter

        //Updating RecyclerView live-time
        viewModel.accountOperations.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.ops)
        })

        return binding.root
    }


}
