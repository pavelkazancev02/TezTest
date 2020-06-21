package com.pavelkazancev02.teztest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pavelkazancev02.teztest.R
import com.pavelkazancev02.teztest.TezTestApp
import com.pavelkazancev02.teztest.data.api.Responder

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        Responder.isLoggedIn = 1
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomBar?.setupWithNavController(navController)

    }

}
