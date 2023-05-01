package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Other : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val recyclerview = findViewById<RecyclerView>(R.id.rv_team)

        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        adapter = TeamRecyclerAdapter()
        recyclerview.adapter = adapter

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