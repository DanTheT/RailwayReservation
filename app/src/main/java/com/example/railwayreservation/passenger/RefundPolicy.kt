package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.passengerTrain.TrainMainActivity

class RefundPolicy : AppCompatActivity() {

    private lateinit var btnNext: Button
    private lateinit var chkAgreement: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refund_policy)

        btnNext = findViewById(R.id.btnNext)
        chkAgreement = findViewById(R.id.chkAgreement)

        btnNext.setOnClickListener {

            if (chkAgreement.isChecked)
                startActivity(Intent(this,PassengerDatePicker::class.java))
            else {
                Toast.makeText(this, "Please agree to the terms and conditions to proceed further.", Toast.LENGTH_SHORT).show()
            }



        }
    }
}