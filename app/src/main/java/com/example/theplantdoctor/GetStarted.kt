package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GetStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val btnLogin = findViewById<Button>(R.id.btn_login_started)
        val btnSignup = findViewById<Button>(R.id.btn_signup_started)

        btnLogin.setOnClickListener {
            val mAuth = Firebase.auth
            val currentUser = mAuth.currentUser

            if (currentUser!=null){
                startActivity(Intent(this@GetStarted, Homepage::class.java))
            } else {
            startActivity(Intent(this@GetStarted, Login::class.java))
            }
        }
        btnSignup.setOnClickListener {
            startActivity(Intent(this@GetStarted, SignUp::class.java))
        }
    }
}