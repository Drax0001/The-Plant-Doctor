package com.example.theplantdoctor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class PlantDetails : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)

        val imageId = intent.getIntExtra("imageId", 0)
        val plantName = intent.getStringExtra("title")
        val plantTitle = findViewById<TextView>(R.id.tv_plant_name)
        val imageDetail = findViewById<ImageView>(R.id.iv_img_detail)
        val addToGarden = findViewById<ImageView>(R.id.iv_add_plant)

        imageDetail.setImageResource(imageId)
        plantTitle.text = plantName

        addToGarden.setOnClickListener {
            addToGarden.setImageResource(R.drawable.baseline_star_24)
            intent.putExtra("image", imageId)
            intent.putExtra("plantname", plantName)

            Toast.makeText(this@PlantDetails, "This plant has been added to My Garden", Toast.LENGTH_SHORT).show()
        }
    }
}