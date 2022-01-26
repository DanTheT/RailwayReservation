package com.example.railwayreservation.passenger

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.railwayreservation.R

class PassengerPayment : AppCompatActivity() {

    private lateinit var btnMakePayment: Button
    private lateinit var btnViewWallet: Button
    private lateinit var btnBackPay: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_payment)

        btnMakePayment = findViewById(R.id.btnMakePayment)
        btnMakePayment.setOnClickListener {
            val makePayURL = Intent(android.content.Intent.ACTION_VIEW)
            makePayURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
            startActivity(makePayURL)
        }

        btnViewWallet = findViewById(R.id.btnViewWallet)
        btnViewWallet.setOnClickListener {
            val viewWalletURL = Intent(android.content.Intent.ACTION_VIEW)
        viewWalletURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/money/")
            startActivity(viewWalletURL)
        }

        btnBackPay = findViewById(R.id.btnBackPay)
        btnBackPay.setOnClickListener {
            startActivity(Intent(this,PassengerHome::class.java))
        }

    }
}