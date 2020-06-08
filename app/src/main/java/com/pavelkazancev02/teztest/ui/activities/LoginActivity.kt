package com.pavelkazancev02.teztest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pavelkazancev02.teztest.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_login_btn.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}