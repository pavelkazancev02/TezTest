package com.pavelkazancev02.teztest.ui.settings

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.pavelkazancev02.teztest.data.Variables.NETWORK_TYPE
import com.pavelkazancev02.teztest.data.api.Responder
import com.pavelkazancev02.teztest.data.api.reBuildRetrofit
import com.pavelkazancev02.teztest.databinding.FragmentSettingsBinding
import com.pavelkazancev02.teztest.ui.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSettingsBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        val settingsViewModelFactory = SettingsViewModelFactory(application)

        val settingsViewModel = ViewModelProviders.of(this, settingsViewModelFactory).get(SettingsViewModel::class.java)

        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = settingsViewModel

        settingsViewModel.networkTypeItemPosition.observe(this, Observer {
            changeNetwork ->
            changeNetwork?.let {
                if (settingsViewModel.networkTypeItemPosition.value == 1) {
                    NETWORK_TYPE = "Carthagenet"
                    reBuildRetrofit()
                }
                else {
                    NETWORK_TYPE = "Mainnet"
                    reBuildRetrofit()
                }
            }
        })

        auth = FirebaseAuth.getInstance()

        binding.settingsExit.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this.context, LoginActivity::class.java))
            Responder.isLoggedIn=0
            activity?.finish()
        }

        return binding.root
    }
}
