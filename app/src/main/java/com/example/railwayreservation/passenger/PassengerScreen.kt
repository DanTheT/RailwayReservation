package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.railwayreservation.R

class PassengerScreen : AppCompatActivity() {

    private lateinit var btnGoLogin: Button
    private lateinit var btnGoSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_screen)

        //go to login page
        btnGoLogin = findViewById(R.id.btnGoLogin)
        btnGoLogin.setOnClickListener {
            startActivity(Intent(this,PassengerLogin::class.java))
        }

        //go to sign up page
        btnGoSignup = findViewById(R.id.btnGoSignup)
        btnGoSignup.setOnClickListener {
            startActivity(Intent(this,PassengerSignup::class.java))

        }
    }
}
