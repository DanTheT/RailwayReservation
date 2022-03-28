package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.cancellation.MakeCancellation
import com.example.railwayreservation.passenger.reservation.Reservation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MakeReservations : AppCompatActivity() {

    private lateinit var etReservationDate: EditText
    private lateinit var etTrainName: EditText
    private lateinit var etCoach: EditText
    private lateinit var etOrigin: EditText
    private lateinit var etDestination: EditText
    private lateinit var etArrival: EditText
    private lateinit var etReach: EditText
    private lateinit var btnSave: Button
    private lateinit var btnNext: Button

    private lateinit var myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservations)

        //save reservation records
        etReservationDate = findViewById(R.id.etReservationDate)
        etTrainName = findViewById(R.id.etTrainName)
        etCoach = findViewById(R.id.etCoach)
        etOrigin = findViewById(R.id.etOrigin)
        etDestination = findViewById(R.id.etDestination)
        etArrival = findViewById(R.id.etArrival)
        etReach = findViewById(R.id.etReach)
        btnSave = findViewById(R.id.btnSave)
        btnNext = findViewById(R.id.btnNext)


        btnSave.setOnClickListener {
            checkInput()

        }

        btnNext.setOnClickListener {
            saveReservation()
            startActivity(Intent(this, TicketActivity::class.java))


        }

    }



    private fun saveReservation() {

        val reservationDate = etReservationDate.text.toString().trim()
        val trainName = etTrainName.text.toString().trim()
        val coach = etCoach.text.toString().trim()
        val origin = etOrigin.text.toString().trim()
        val destination = etDestination.text.toString().trim()
        val arriveTime = etArrival.text.toString().trim()
        val reachTime = etReach.text.toString().trim()


        val myRef = FirebaseDatabase.getInstance().getReference("Reservations")

        val transactionID = myRef.push().key.toString()

        val reservation = Reservation(
            transactionID,
            reservationDate,
            trainName,
            coach,
            origin,
            destination,
            arriveTime,
            reachTime
        )

        myRef.child(transactionID).setValue(reservation).addOnCompleteListener {
            Toast.makeText(applicationContext, "Your reservation has been made.", Toast.LENGTH_LONG)
                .show()

        }
    }

    private fun checkInput() {
        if (TextUtils.isEmpty(etReservationDate.text.toString())) {
            etReservationDate.error = "Enter reservation date"
            return
        } else if (TextUtils.isEmpty(etTrainName.text.toString())) {
            etTrainName.error = "Enter train line"
            return
        } else if (TextUtils.isEmpty(etCoach.text.toString())) {
            etCoach.error = "Enter coach you selected"
            return
        } else if (TextUtils.isEmpty(etOrigin.text.toString())) {
            etOrigin.error = "Enter origin"
            return
        } else if (TextUtils.isEmpty(etDestination.text.toString())) {
            etDestination.error = "Enter destination"
            return
        } else if (TextUtils.isEmpty(etArrival.text.toString())) {
            etArrival.error = "Enter the arrival time"
            return
        } else if (TextUtils.isEmpty(etReach.text.toString())) {
            etReach.error = "Enter the reach time"
            return
        } else {
            Toast.makeText(applicationContext, "Reservation details has all been entered. You may proceed.", Toast.LENGTH_LONG)
                .show()
        }
    }
}


