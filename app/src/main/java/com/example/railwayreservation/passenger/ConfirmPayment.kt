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

    private lateinit var etReservationDate: EditText
    private lateinit var etSeatCategory: EditText
    private lateinit var etTotalAmount: EditText
    private lateinit var btnSave: Button
    private lateinit var btnNext: Button
    private lateinit var btnDoneTicket: Button
    private lateinit var btnGoPay: Button

    private lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_payment)

        etReservationDate = findViewById(R.id.etReservationDate)
        etSeatCategory = findViewById(R.id.etSeatCategory)
        etTotalAmount = findViewById(R.id.etTotalAmount)
        btnSave = findViewById(R.id.btnSave)
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

        btnSave.setOnClickListener {
            checkInput()

        }

        btnNext.setOnClickListener {
            saveReservation()
            val tvSuccess = findViewById<TextView>(R.id.tvSuccess)
            tvSuccess.isVisible = true
            btnGoPay.isEnabled = true

        }

    }



    private fun saveReservation() {

        val reservationDate = etReservationDate.text.toString().trim()
        val seatCategory =   etSeatCategory.text.toString().trim()
        val totalAmount = etTotalAmount.text.toString().trim()



        val myRef = FirebaseDatabase.getInstance().getReference("Tickets")

        val transactionID = myRef.push().key.toString()

        val ticket = Ticket(
            transactionID,
            reservationDate,
            seatCategory,
            totalAmount
        )

        myRef.child(transactionID).setValue(ticket).addOnCompleteListener {
            Toast.makeText(applicationContext, "Your reservation has been made.", Toast.LENGTH_LONG)
                .show()

        }
    }

    private fun checkInput() {
        if (TextUtils.isEmpty(etReservationDate.text.toString())) {
            etReservationDate.error = "Enter reservation date"
            return
        } else if (TextUtils.isEmpty(etSeatCategory.text.toString())) {
            etSeatCategory.error = "Enter seat category. Example: Adult x2 type as Ax2."
            return
        } else if (TextUtils.isEmpty(etTotalAmount.text.toString())) {
            etTotalAmount.error = "Enter coach you selected"
            return
        } else {
            Toast.makeText(applicationContext, "Ticket details has all been entered. You may proceed to make payment.", Toast.LENGTH_LONG)
                .show()
        }
    }


}