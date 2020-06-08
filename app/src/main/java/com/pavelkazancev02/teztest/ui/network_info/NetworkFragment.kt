package com.pavelkazancev02.teztest.ui.network_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.databinding.FragmentNetworkBinding
import com.pavelkazancev02.teztest.ui.subscriptions.SubscriptionsViewModel
import com.pavelkazancev02.teztest.ui.subscriptions.SubscriptionsViewModelFactory
import kotlinx.android.synthetic.main.fragment_network.*

class NetworkFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentNetworkBinding.inflate(inflater)


        val application = requireNotNull(this.activity).application

        val viewModelFactory =NetworkInfoViewModelFactory(application)

        val networkInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(NetworkInfoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = networkInfoViewModel


//        binding.networkSearchButton.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_networkInfoFragment_to_accountInfoFragment)
//        }

        networkInfoViewModel.navigateToAccountInfo.observe(this, Observer {
                navEvent ->
            navEvent?.let {
                this.findNavController().navigate(
                    NetworkFragmentDirections.actionNetworkInfoFragmentToAccountInfoFragment(navEvent))
                networkInfoViewModel.doneNavigating()
            }
        })

        return binding.root
    }


}
