package com.pavelkazancev02.teztest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pavelkazancev02.teztest.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        btn_sign_up.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        login_login_btn.setOnClickListener{
            doLogin()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if (currentUser!=null){
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }

    private fun doLogin(){

        if(login_email.text.toString().isEmpty()){
            login_email.error = "Please, enter an email"
            login_email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(login_email.text.toString()).matches()){
            login_email.error = "Please, enter a valid email"
            login_email.requestFocus()
            return
        }

        if(login_password.text.toString().isEmpty()){
            login_password.error = "Please, enter a password"
            login_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(login_email.text.toString(), login_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "User does not exist",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
}