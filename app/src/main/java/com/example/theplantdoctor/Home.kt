package com.example.theplantdoctor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val cardView = findViewById<CardView>(R.id.cv_plant_diseases)
        val chevron = findViewById<ImageView>(R.id.iv_chevron)
        val chatbotAi = findViewById<CardView>(R.id.cv_chatbot_AI)
        val greetings = findViewById<TextView>(R.id.tv_greetings)
        val profilePic = findViewById<ImageView>(R.id.iv_profile )
        val plant = findViewById<CardView>(R.id.cv_ai_plant)
        val database = FirebaseDatabase.getInstance().reference
        val careTips = findViewById<TextView>(R.id.tv_tips)

        val tipsArray = arrayOf(
            "Choose plants based on your light",
            "Pick plants that work with your schedule",
            "Be mindful when watering",
            "Raise humidity levels when needed",
            "Always keep temperatures stable",
            "Know when to skip the fertilizer",
            "Shop from a reliable source",
            "Show a little TLC at first",
            "Don’t be scared to repot",
            "Keep drainage in mind"
        )

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

        fun  plantCareTips(){
            val randomTip = Random.nextInt(9)
            careTips.text = tipsArray[randomTip]
        }

        plantCareTips()
    }
}
