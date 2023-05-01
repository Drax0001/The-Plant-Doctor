package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Other : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@Other, Homepage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@Other, AiRecognition::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miCamera -> {
                    val intent = Intent(this@Other, ChatPage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miYou -> {
                    val intent = Intent(this@Other, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
//                R.id.miOther-> {
//                    val intent = Intent(this@Other, Other::class.java)
//                    startActivity(intent)
//
//                    return@setOnItemSelectedListener true
//                }

            }
            false
        }
    }
}