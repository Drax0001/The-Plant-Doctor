package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val mAuth = Firebase.auth
            val currentUser = mAuth.currentUser

            if (currentUser!=null){
                startActivity(Intent(this@Splash, Homepage::class.java))
                finish()
            } else {
                val intent = Intent(this@Splash, Onboarding::class.java)
                startActivity(intent)
                finish()
            }
        },4000)
    }
}