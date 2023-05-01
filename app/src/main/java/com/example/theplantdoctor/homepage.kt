package com.example.theplantdoctor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class Homepage :AppCompatActivity(){

    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerPlant? = null
    private var plantList = mutableListOf<Plant>()
    private lateinit var searchView:SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val robot= findViewById<CardView>(R.id.robot)
        val chatPage = findViewById<CardView>(R.id.cv_chat_doctors)
        val diseaseDico = findViewById<CardView>(R.id.cv_disease_encyclopedia)
        val homePicture = findViewById<ImageView>(R.id.iv_home_profile)

        robot.setOnClickListener{
            startActivity(Intent(this,Chatbot::class.java))
        }
        chatPage.setOnClickListener{
            startActivity(Intent(this,ChatPage::class.java))
        }
        diseaseDico.setOnClickListener{
            startActivity(Intent(this,Diseases::class.java))
        }
        homePicture.setOnClickListener{
            startActivity(Intent(this,Profile::class.java))
        }

        searchView = findViewById(R.id.Search)
        searchView.clearFocus()


        plantList = ArrayList()

        recyclerView = findViewById<View>(R.id.plant_card) as RecyclerView
        recyclerViewMovieAdapter = RecyclerPlant(this@Homepage, plantList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewMovieAdapter

        preparePlantListData()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true

            }

        })

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome -> {
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
                R.id.miOther-> {
                    val intent = Intent(this@Homepage, Other::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }


    }
    private fun filterList(query:String?){
        if(query!=null){
            val filteredList= ArrayList<Plant>()
            for(i in plantList){
                if(i.title.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }else{
               recyclerViewMovieAdapter!!.setfilteredList(filteredList)

            }
        }
    }

    private fun preparePlantListData() {
        var plant = Plant( R.drawable.plant1,"Monstera")
        plantList.add(plant)
        plant = Plant( R.drawable.plant3, "Dracaena")
        plantList.add(plant)
        plant= Plant( R.drawable.plant4, "Peperomia")
        plantList.add(plant)
        plant = Plant( R.drawable.plant5, "planla")
        plantList.add(plant)
        plant = Plant( R.drawable.plant2, "Monstera")
        plantList.add(plant)
        plant = Plant( R.drawable.plant6, "Pleomele")
        plantList.add(plant)
        plant = Plant( R.drawable.plant7, "Royal Philodendrons")
        plantList.add(plant)
        plant = Plant( R.drawable.plant8, "Red heart")
        plantList.add(plant)
        plant = Plant( R.drawable.plant9, "Pothos")
        plantList.add(plant)
        plant= Plant( R.drawable.plant11, "Monstera")
        plantList.add(plant)
        plant = Plant( R.drawable.plant15, "Exotica")
        plantList.add(plant)
        plant = Plant( R.drawable.plant12, "Monstera")
        plantList.add(plant)
        plant = Plant( R.drawable.plant13, "Marble")
        plantList.add(plant)
        plant = Plant( R.drawable.plant14, "pilea")
        plantList.add(plant)
        plant = Plant( R.drawable.plant16, "indoor cactus")
        plantList.add(plant)
        plant = Plant( R.drawable.plant17, "Laurentii")
        plantList.add(plant)
        plant = Plant( R.drawable.plant18, "Althernanthera")
        plantList.add(plant)
        plant = Plant( R.drawable.plant19, "Leaves")
        plantList.add(plant)
        plant = Plant( R.drawable.plant20, "Fiddle leaf fig")
        plantList.add(plant)
        plant = Plant( R.drawable.plant21, "Monstera")
        plantList.add(plant)


        recyclerViewMovieAdapter!!.notifyDataSetChanged()

        //All Code is done let's run the app
    }
}
