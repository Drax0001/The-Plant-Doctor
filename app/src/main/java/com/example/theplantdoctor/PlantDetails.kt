package com.example.theplantdoctor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PlantDetails : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)

        val imageId = intent.getIntExtra("imageId", 0)
        val plantName = intent.getStringExtra("title")
        val plantTitle = findViewById<TextView>(R.id.tv_plant_name)
        val imageDetail = findViewById<ImageView>(R.id.iv_img_detail)

        imageDetail.setImageResource(imageId)
        plantTitle.text = plantName
    }
}