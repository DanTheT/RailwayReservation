package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.railwayreservation.R

class PassengerFAQ : AppCompatActivity() {

    private lateinit var btnTrainRoute: Button
    private lateinit var btnSeatPlan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_faq)

        btnTrainRoute = findViewById(R.id.btnTrainRoute)
        btnSeatPlan = findViewById(R.id.btnSeatPlan)

        btnTrainRoute.setOnClickListener {
            startActivity(Intent(this, TrainRouteImage::class.java))
        }

        btnSeatPlan.setOnClickListener {
            startActivity(Intent(this, SeatPlanImage::class.java))
        }

    }
}