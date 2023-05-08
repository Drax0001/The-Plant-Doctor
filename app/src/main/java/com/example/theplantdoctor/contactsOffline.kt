package com.example.theplantdoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class contactsOffline : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_offline)

        //        codes to make the textVies clickable so it can refer you to a number
        var personName1 = findViewById<TextView>(R.id.personName1);
        personName1.movementMethod = LinkMovementMethod.getInstance();

        var personName2 = findViewById<TextView>(R.id.personName2);
        personName2.movementMethod = LinkMovementMethod.getInstance();

        var personName3 = findViewById<TextView>(R.id.name3);
        personName3.movementMethod = LinkMovementMethod.getInstance();

        var personName4 = findViewById<TextView>(R.id.name4);
        personName4.movementMethod = LinkMovementMethod.getInstance();

        var personName5 = findViewById<TextView>(R.id.name5);
        personName5.movementMethod = LinkMovementMethod.getInstance();

        var personName6 = findViewById<TextView>(R.id.name6);
        personName6.movementMethod = LinkMovementMethod.getInstance();

        var personName7 = findViewById<TextView>(R.id.name7);
        personName7.movementMethod = LinkMovementMethod.getInstance();

        var personName8 = findViewById<TextView>(R.id.name8);
        personName8.movementMethod = LinkMovementMethod.getInstance();

    }
}