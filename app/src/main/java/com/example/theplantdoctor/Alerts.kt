package com.example.theplantdoctor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class Alerts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)
        val alertbtn = findViewById<Button>(R.id.btn_show_alert)

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Important")
            .setMessage("Be as accurate as possible with the AI bot to draw out the best solution for your plant")
            .setIcon(R.drawable.chat_bot)
            .setPositiveButton("OK, Got it!") { _, _ ->
                Toast.makeText(this, "Thank you for understanding", Toast.LENGTH_SHORT).show()
            }.create()

        alertbtn.setOnClickListener {
            addContactDialog.show()
        }
    }
}