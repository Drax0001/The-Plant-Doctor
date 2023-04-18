package com.example.theplantdoctor

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var btnLogin: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtUsername = findViewById(R.id.edtUsername)
        btnSignup = findViewById(R.id.btnSignup)
        btnLogin = findViewById(R.id.btn_login_redirect)

        btnLogin.setOnClickListener {
            val intent = Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }


        btnSignup.setOnClickListener {
            val name = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name, email, password)
        }

    }

    private fun signUp(name:String, email: String, password: String){
        if(email.isEmpty()) {
            edtEmail.error = "Please enter your email"
            edtEmail.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()) {
            edtEmail.error = "Please enter a valid email"
            return
        }
        if(password.isEmpty()) {
            edtPassword.error = "Oops! Enter your password"
            edtPassword.requestFocus()
            return
        }

        //logic for creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                   val intent = Intent(this@SignUp, Home::class.java)
                    intent.putExtra("username", name)
                    finish()
                    startActivity(intent)

                    Toast.makeText(this@SignUp, "Thank you for creating an account on the plant doctor", Toast.LENGTH_LONG).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp, "Some error occurred!", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name:String, email:String, uid:String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }

}