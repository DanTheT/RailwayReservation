package com.example.railwayreservation.admin.refund

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.AdminMainFragment
import com.example.railwayreservation.passenger.RefundPolicy

class GoRefund : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_refund)

        val btnViewRequest = findViewById<Button>(R.id.btnViewRequest)
        btnViewRequest.setOnClickListener {
            startActivity(Intent(this,MakeRefund::class.java))

        }

        val btnMakeRefunds = findViewById<Button>(R.id.btnMakeRefunds)
        btnMakeRefunds.setOnClickListener {
            val makeRefundURL = Intent(android.content.Intent.ACTION_VIEW)
            makeRefundURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
            startActivity(makeRefundURL)
        }

        val btnDone = findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener {
            startActivity(Intent(this,AdminMainFragment::class.java))

        }

    }
}