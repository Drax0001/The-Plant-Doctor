package com.example.theplantdoctor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PlantDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)

        val imageId = intent.getIntExtra("imageId", 0)
        val imageDetail = findViewById<ImageView>(R.id.iv_img_detail)

        imageDetail.setImageResource(imageId)
    }
}