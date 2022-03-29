package com.example.railwayreservation.passenger

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.models.CategoryModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfirmPayment : AppCompatActivity() {


    private lateinit var btnSave: Button
    private lateinit var btnNext: Button
    private lateinit var btnDoneTicket: Button
    private lateinit var btnGoPay: Button

    private lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)


        btnNext = findViewById(R.id.btnNext)
        btnGoPay = findViewById(R.id.btnGoPay)
        btnDoneTicket = findViewById(R.id.btnDoneTicket)


        btnGoPay.setOnClickListener {
            val makePayURL = Intent(android.content.Intent.ACTION_VIEW)
            makePayURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
            startActivity(makePayURL)
        }

        btnDoneTicket.setOnClickListener {
            startActivity(Intent(this,PassengerHome::class.java))

        }

        btnNext.setOnClickListener {
            val tvSuccess = findViewById<TextView>(R.id.tvSuccess)
            tvSuccess.isVisible = true
            btnGoPay.isEnabled = true

        }

    }
}