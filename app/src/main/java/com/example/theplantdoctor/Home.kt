package com.example.theplantdoctor

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val name = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
        val cardView = findViewById<CardView>(R.id.cv_plant_diseases)
        val chevron = findViewById<ImageView>(R.id.iv_chevron)
        val chatbotAi = findViewById<CardView>(R.id.cv_chatbot_AI)
        val greetings = findViewById<TextView>(R.id.tv_greetings)
        val profilePic = findViewById<ImageView>(R.id.iv_profile )
        val plant=findViewById<CardView>(R.id.cv_ai_plant)

        greetings.text = "Hello, "+ username

        chatbotAi.setOnClickListener {
            val intent = Intent(this@Home, Chatbot::class.java)
            startActivity(intent)
        }
        plant.setOnClickListener {
            val intent = Intent(this@Home, AiRecognition::class.java)
            startActivity(intent)
        }

        cardView.setOnClickListener {
            chevron.animate().apply {
                duration = 300
                scaleXBy(0.5f)
                scaleYBy(0.5f)
            }.withEndAction {
                chevron.animate().apply {
                    duration = 300
                    scaleXBy(-.5f)
                    scaleYBy(-.5f)
                }
            }.start()


            val intent = Intent(this@Home, Diseases::class.java)
            startActivity(intent)
        }

        //Bottom nav click listeners
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@Home, Home::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@Home, ChatPage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miCamera -> {
                    val intent = Intent(this@Home, AiRecognition::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miYou -> {
                    val intent = Intent(this@Home, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }
}
