package com.example.theplantdoctor

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import de.hdodenhof.circleimageview.CircleImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Profile : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 100
    }

    private lateinit var btnLogOut: Button
    private lateinit var userName: TextView
    private lateinit var email: TextView
    private lateinit var profilePicture: ImageView
    private lateinit var ivProfilePicture: CircleImageView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storageRef: StorageReference
    private lateinit var mDbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().reference
        val database = FirebaseDatabase.getInstance().reference

        btnLogOut = findViewById(R.id.btn_logout)
        userName = findViewById(R.id.profile_username)
        email = findViewById(R.id.profile_email)
        profilePicture = findViewById(R.id.profile_pic)
        ivProfilePicture = findViewById(R.id.iv_profile_pic)




        ivProfilePicture.setOnClickListener {
            // Open gallery to select a new profile picture
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }

        // Retrieve user profile data from Firebase Realtime Database
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val databaseRef = FirebaseDatabase.getInstance().reference.child("users/${currentUser.uid}")
            databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val displayName = snapshot.child("name").value as? String
                    val displayEmail = snapshot.child("email").value as? String
                    userName.text = displayName
                    email.text = displayEmail

                    // Load profile picture from Firebase Realtime Database
                    val profilePictureUrl = snapshot.child("profile_picture").value as? String
                    if (profilePictureUrl != null) {
                        Glide.with(this@Profile).load(profilePictureUrl).into(ivProfilePicture)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }

        val userRef = database.child("user")

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val currentUserRef = userRef.child(uid!!)

        currentUserRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val displayName = dataSnapshot.child("name").getValue(String::class.java)
                val displayEmail = dataSnapshot.child("email").getValue(String::class.java)
                Log.d(TAG, "Username: $displayName")
                Log.d(TAG, "Email: $displayEmail")
                userName.text = displayName
                email.text = displayEmail
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "Database error: ${databaseError.message}")
            }
        })

        btnLogOut.setOnClickListener {
            mAuth.signOut()
            finish()
            startActivity(Intent(this@Profile, Login::class.java))
        }

        //Bottom nav click listeners
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationView.bringToFront()

        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.miHome-> {
                    val intent = Intent(this@Profile, Home::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miChat -> {
                    val intent = Intent(this@Profile, AiRecognition::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miCamera -> {
                    val intent = Intent(this@Profile, ChatPage::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.miYou -> {
                    val intent = Intent(this@Profile, Profile::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            // Get selected image Uri
            val imageUri = data.data

            // Upload image to Firebase Realtime Database
            val currentUser = mAuth.currentUser
            if (currentUser != null) {
                val databaseRef = FirebaseDatabase.getInstance().reference.child("users/${currentUser.uid}")
                val profilePictureRef = storageRef.child("users/${currentUser.uid}/profile_picture.jpg")
                if (imageUri != null) {
                    profilePictureRef.putFile(imageUri)
                        .addOnSuccessListener { taskSnapshot ->
                            // Get download URL from Firebase Storage
                            profilePictureRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                                // Save download URL to Firebase Realtime Database
                                databaseRef.child("profile_picture").setValue(downloadUrl.toString())
                                    .addOnSuccessListener {
                                        // Update profile picture ImageView with new image
                                        Glide.with(this).load(downloadUrl).into(ivProfilePicture)
                                    }
                                    .addOnFailureListener { exception ->
                                        // Handle error
                                    }
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Handle error
                        }
                }
            }
        }
    }
}