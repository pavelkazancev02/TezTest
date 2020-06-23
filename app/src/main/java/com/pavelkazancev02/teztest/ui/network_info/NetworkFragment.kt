package com.pavelkazancev02.teztest.ui.network_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.pavelkazancev02.teztest.databinding.FragmentNetworkBinding

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

        networkInfoViewModel.navigateToInfo.observe(this, Observer {
                navEvent ->
            navEvent?.let {
                if (navEvent.length==36){
                this.findNavController().navigate(
                    NetworkFragmentDirections.actionNetworkInfoFragmentToAccountInfoFragment(navEvent))
                networkInfoViewModel.doneNavigating()
                }
                else if (navEvent.length==51){
                    this.findNavController().navigate(
                        NetworkFragmentDirections.actionNetworkInfoFragmentToTransactionInfoFragment(navEvent))
                    networkInfoViewModel.doneNavigating()
                }
            }
        })

        return binding.root
    }


}
