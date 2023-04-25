package com.example.theplantdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatPage : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_page)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        userRecyclerView = findViewById(R.id.rv_users)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        mDbRef.child("user").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)

                    if(mAuth.currentUser?.uid!=currentUser?.uid){
                        userList.add(currentUser!!)
                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        //Menu items click listeners

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@ChatPage, Homepage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@ChatPage, ChatPage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
               R.id.miCamera -> {
                   val intent = Intent(this@ChatPage, AiRecognition::class.java)
                  startActivity(intent)
                  return@setOnItemSelectedListener true
              }
                R.id.miYou -> {
                    val intent = Intent(this@ChatPage, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }
}
