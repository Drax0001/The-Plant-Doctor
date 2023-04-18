package com.example.theplantdoctor

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import okio.IOException

class AiRecognition : AppCompatActivity() {
    private lateinit var mCategorization: Categorization
    private lateinit var mBitmap: Bitmap
    private val mCameraRequestCode = 0
    private val mGalleryRequestCode = 2

    private val mInputSize = 224
    private val mModelPath = "plant_disease_model.tflite"
    private val mLabelPath = "plant_labels.txt"

    private val mSamplePath = "automn.jpg"

    private lateinit var mCamera: Button
    private lateinit var mGallery: Button
    private lateinit var mDetect: Button
    private lateinit var photo: ImageView
    private lateinit var mresults: TextView

    private val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->

        if(result.resultCode == Activity.RESULT_OK){
            val bitmap = result?.data?.extras?.get("data") as Bitmap

            photo.setImageBitmap(bitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_recognition)

        mCamera = findViewById(R.id.btn_camera)
        mGallery = findViewById(R.id.btn_gallery)
        mDetect = findViewById(R.id.btn_detect)
        photo = findViewById(R.id.iv_photo)
        mresults = findViewById(R.id.tv_results)

        mCategorization = Categorization(assets, mModelPath, mLabelPath, mInputSize)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@AiRecognition, Home::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@AiRecognition, ChatPage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miCamera -> {
                    val intent = Intent(this@AiRecognition, AiRecognition::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miYou -> {
                    val intent = Intent(this@AiRecognition, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }

        resources.assets.open(mSamplePath).use{
            mBitmap = BitmapFactory.decodeStream(it)
            mBitmap = Bitmap.createScaledBitmap(mBitmap, mInputSize, mInputSize, true)
            photo.setImageBitmap(mBitmap)
        }

        mCamera.setOnClickListener {
//            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(callCameraIntent, mCameraRequestCode)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultContract.launch(intent)
        }
        mGallery.setOnClickListener {
            val callGalleryIntent = Intent(MediaStore.ACTION_PICK_IMAGES)
            callGalleryIntent.type = "image/*"
            resultContract.launch(callGalleryIntent)
        }
        mDetect.setOnClickListener {
            val progressDialog = ProgressDialog(this@AiRecognition)
            progressDialog.setTitle("Please Wait...")
            progressDialog.setMessage("AI is processing...")
            progressDialog.show()
            val handler = Handler()
            handler.postDelayed(Runnable { progressDialog.dismiss()
            val results = mCategorization.recognizeImage(mBitmap).firstOrNull()
                mresults.text = results?.title + "\n Confidence: "+results?.confidence
            }, 2000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == mCameraRequestCode) {
            if(resultCode == Activity.RESULT_OK && data!=null) {
                mBitmap = data.extras!!.get("data") as Bitmap
                mBitmap = scaleImage(mBitmap)
                photo.setImageBitmap(mBitmap)
                mresults.text="Your photo is set now."
            } else {
                Toast.makeText(this, "Camera cancel..", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == mCameraRequestCode) {
            if(data!=null){
                val uri = data.data
                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                }
                catch (e: IOException){
                    e.printStackTrace()
                }

                mBitmap = scaleImage(mBitmap)
                photo.setImageBitmap(mBitmap)
            }
        }
    }

    private fun scaleImage(bitmap: Bitmap): Bitmap {
        val originalWidth = bitmap.width
        val originalHeight = bitmap.height
        val scaleWidth = mInputSize.toFloat()
        val scaleHeight = mInputSize.toFloat()
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap,0,0,originalWidth, originalHeight, matrix, true)
    }


}
