package com.example.railwayreservation.passenger

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.models.RestaurentModel

class ConfirmPayment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)

        val restaurantModel: RestaurentModel? = intent.getParcelableExtra("RestaurantModel")
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setTitle(restaurantModel?.name)
        actionbar?.setSubtitle(restaurantModel?.address)
        actionbar?.setDisplayHomeAsUpEnabled(false)


        val btnGoPay = findViewById<TextView>(R.id.btnGoPay)
        btnGoPay.setOnClickListener {
            val makePayURL = Intent(android.content.Intent.ACTION_VIEW)
            makePayURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
            startActivity(makePayURL)
        }

        val buttonDone = findViewById<TextView>(R.id.buttonDone)
        buttonDone.setOnClickListener {
            startActivity(Intent(this,PassengerHome::class.java))

        }
    }
}