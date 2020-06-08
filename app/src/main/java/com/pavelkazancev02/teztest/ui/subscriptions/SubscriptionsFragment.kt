package com.pavelkazancev02.teztest.ui.subscriptions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pavelkazancev02.teztest.databinding.FragmentSubscriptionsBinding
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.data.room_db.SubscriptionsDatabase
import com.pavelkazancev02.teztest.ui.subscriptions.recycler_view.SubscribedAccountsAdapter
import com.pavelkazancev02.teztest.ui.subscriptions.recycler_view.InfoClickListener
import com.pavelkazancev02.teztest.ui.subscriptions.recycler_view.UnsubscribeClickListener

class SubscriptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSubscriptionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_subscriptions, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = SubscriptionsDatabase.getInstance(application).subscriptionsDatabaseDao

        val viewModelFactory = SubscriptionsViewModelFactory(dataSource, application)

        val subscriptionsViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(SubscriptionsViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.subscriptionsViewModel = subscriptionsViewModel

        //RecyclerView Adapter
        val adapter = SubscribedAccountsAdapter(InfoClickListener {
                accountAddress ->  subscriptionsViewModel.onInfoClicked(accountAddress)
        }, UnsubscribeClickListener {
            accountAddress ->  subscriptionsViewModel.onUnsubscribeClicked(accountAddress)
        })

        binding.subscriptionsRecyclerView.adapter = adapter

        //Updating RecyclerView live-time
        subscriptionsViewModel.allAccounts.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        //Navigation to the Account Info
        subscriptionsViewModel.navigateToAccountInfo.observe(this, Observer {
                navEvent ->
            navEvent?.let {
                this.findNavController().navigate(
                    SubscriptionsFragmentDirections.actionSubscriptionsFragmentToAccountInfoFragment(navEvent))
                    subscriptionsViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
