package com.pavelkazancev02.teztest.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pavelkazancev02.teztest.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        register_register_btn.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser(){
        if(register_email.text.toString().isEmpty()){
            register_email.error = "Please, enter an email"
            register_email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(register_email.text.toString()).matches()){
            register_email.error = "Please, enter a valid email"
            register_email.requestFocus()
            return
        }
        if(register_confirm_email.text.toString()!=register_email.text.toString()){
            register_email.error = "Please, confirm an email"
            register_confirm_email.requestFocus()
            return
        }
        if(register_password.text.toString().isEmpty()){
            register_password.error = "Please, enter a password"
            register_password.requestFocus()
            return
        }
        if(register_confirm_password.text.toString()!=register_password.text.toString()){
            register_confirm_password.error = "Please, confirm a password"
            register_confirm_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(register_email.text.toString(), register_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "User already exists.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}