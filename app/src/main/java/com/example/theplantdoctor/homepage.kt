package com.example.theplantdoctor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homepage :AppCompatActivity(){
    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerPlant? = null
    private var plantList = mutableListOf<Plant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)


        plantList = ArrayList()

        recyclerView = findViewById<View>(R.id.plant_card) as RecyclerView
        recyclerViewMovieAdapter = RecyclerPlant(this@Homepage, plantList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewMovieAdapter

        prepareMovieListData()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@Homepage, Homepage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@Homepage, ChatPage::class.java)
                    startActivity(intent)


                    return@setOnItemSelectedListener true
                }
                R.id.miCamera -> {
                    val intent = Intent(this@Homepage, AiRecognition::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miYou -> {
                    val intent = Intent(this@Homepage, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }


    }

    private fun prepareMovieListData() {
        var plant = Plant( R.drawable.plant1)
        plantList.add(plant)
        plant = Plant( R.drawable.plant2)
        plantList.add(plant)
        plant = Plant( R.drawable.plant3)
        plantList.add(plant)
        plant= Plant( R.drawable.plant4)
        plantList.add(plant)
        plant = Plant( R.drawable.plant5)
        plantList.add(plant)
        plant = Plant( R.drawable.plant6)
        plantList.add(plant)
        plant = Plant( R.drawable.plant7)
        plantList.add(plant)
        plant = Plant( R.drawable.plant8)
        plantList.add(plant)
        plant = Plant( R.drawable.plant9)
        plantList.add(plant)
        plant= Plant( R.drawable.plant11)
        plantList.add(plant)
        plant = Plant( R.drawable.plant15)
        plantList.add(plant)
        plant = Plant( R.drawable.plant12)
        plantList.add(plant)
        plant = Plant( R.drawable.plant13)
        plantList.add(plant)
        plant = Plant( R.drawable.plant14)
        plantList.add(plant)
        plant = Plant( R.drawable.plant16)
        plantList.add(plant)
        plant = Plant( R.drawable.plant17)
        plantList.add(plant)
        plant = Plant( R.drawable.plant18)
        plantList.add(plant)
        plant = Plant( R.drawable.plant19)
        plantList.add(plant)
        plant = Plant( R.drawable.plant20)
        plantList.add(plant)
        plant = Plant( R.drawable.plant21)
        plantList.add(plant)


        recyclerViewMovieAdapter!!.notifyDataSetChanged()

        //All Code is done let's run the app
    }
}
