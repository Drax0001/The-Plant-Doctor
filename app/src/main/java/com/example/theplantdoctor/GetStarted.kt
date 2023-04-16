package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GetStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val btnLogin = findViewById<Button>(R.id.btn_login_started)
        val btnSignup = findViewById<Button>(R.id.btn_signup_started)

        btnLogin.setOnClickListener {
            startActivity(Intent(this@GetStarted, Login::class.java))
        }
        btnSignup.setOnClickListener {
            startActivity(Intent(this@GetStarted, SignUp::class.java))
        }
    }
}