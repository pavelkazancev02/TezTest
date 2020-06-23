package com.pavelkazancev02.teztest.ui.block_info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pavelkazancev02.teztest.databinding.FragmentBlockInfoBinding

class BlockInfoFragment: Fragment() {

    private val viewModel: BlockInfoViewModel by lazy {
        ViewModelProviders.of(this).get(BlockInfoViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBlockInfoBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val arguments = BlockInfoFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = BlockInfoViewModelFactory(arguments.blockHash, application)

        val BlockInfoViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(BlockInfoViewModel::class.java)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = BlockInfoViewModel

        return binding.root
    }

}