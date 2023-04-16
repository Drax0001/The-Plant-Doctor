package com.example.theplantdoctor

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var btnForgotPass: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        btnForgotPass = findViewById(R.id.btn_forgot_pass)

        btnForgotPass.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter your Email")
            val view = layoutInflater.inflate(R.layout.reset_password, null)
            builder.setView(view)
            val userEmail = findViewById<EditText>(R.id.et_email_reset)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                    forgotPassword(userEmail)
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }

        btnSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email, password);
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

    }

    private fun forgotPassword(userEmail: EditText) {
        if (userEmail.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString()).matches()) {
            userEmail.error = "Enter a valid email address"
            return
        }
        mAuth.sendPasswordResetEmail(userEmail.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val addContactDialog = AlertDialog.Builder(this)
                        .setTitle("Email Sent")
                        .setMessage("An E-mail has been sent to your address. Click on the link in it to reset you password, then come back and login with your new password")
                        .setIcon(R.drawable.baseline_mark_email_read_24)
                        .setPositiveButton("OK") { _, _ -> }.create()
                }
            }
    }


    private fun login(email: String, password: String){

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

        //logic for logging a user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@Login, Home::class.java)
                    intent.putExtra("email", email)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "Authentication failed or User doesn't exist.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}