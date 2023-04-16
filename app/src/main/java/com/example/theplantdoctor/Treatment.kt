package com.example.theplantdoctor

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class Treatment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        supportActionBar?.hide()
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageId = intent.getIntExtra("imageId", 0)

        val diseaseTitle = findViewById<TextView>(R.id.tv_disease_title)
        val diseaseProblem = findViewById<TextView>(R.id.tv_disease_problem)
        val diseasePicture = findViewById<ImageView>(R.id.iv_disease_pic)
        val cardImage = findViewById<CardView>(R.id.cv_image_container)
        val saveImage = findViewById<ImageView>(R.id.iv_save_img)

        diseaseTitle.text = title
        diseaseProblem.text = description
        diseasePicture.setImageResource(imageId)

        saveImage.setOnClickListener {
            val bitmap = getImageOfView(diseasePicture)
            if(bitmap!=null) {
                saveToStorage(bitmap)
            }
        }
    }

    private fun saveToStorage(bitmap: Bitmap) {
        val diseaseTitle = findViewById<TextView>(R.id.tv_disease_title)
        val imageName = diseaseTitle.text.toString()
        var fos : OutputStream? = null
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri : Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let {
                    resolver.openOutputStream(it)
                }
            }
        } else {
            val imagesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDirectory, imageName)
            fos = FileOutputStream(image)
        }
        
        fos?.use { 
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Image saved Successfully!", Toast.LENGTH_LONG).show()
        }
    }

    private fun getImageOfView(view: ImageView): Bitmap? {
        var image: Bitmap? = null
        try{
            image = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            view.draw(canvas)
        } catch (e: Exception) {
            Log.e("Can't capture", "Try again")
        }
        return image
    }
}