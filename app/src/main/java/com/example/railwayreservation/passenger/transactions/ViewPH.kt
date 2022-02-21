package com.example.railwayreservation.passenger.transactions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.PassengerTicket
import com.google.firebase.database.*

class ViewPH : AppCompatActivity() {

//    private lateinit var etReservationDate : EditText
//    private lateinit var etTrainName : EditText
//    private lateinit var etCoach : EditText
//    private lateinit var etSeatCat : EditText
//    private lateinit var etSeats : EditText
//    private lateinit var etFrom : EditText
//    private lateinit var etTo : EditText
//    private lateinit var etArrival : EditText
//    private lateinit var etReach : EditText
////    private lateinit var etStatus : EditText
//    private lateinit var btnSave : Button
//    private lateinit var btnClear : Button

    private lateinit var myRef: DatabaseReference
    private lateinit var transactionList: MutableList<Transaction>
    private lateinit var transactionListView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ph)



        transactionList = mutableListOf()

        val myRef = FirebaseDatabase.getInstance().getReference("Transactions")

        transactionListView = findViewById(R.id.transactionListView)

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {

                    for (h in snapshot.children) {
                        val transaction = h.getValue(Transaction::class.java)
                        transactionList.add((transaction!!))
                    }
                }

                val adapter =
                    TransactionAdapter(this@ViewPH, R.layout.transaction, transactionList)
                transactionListView.adapter = adapter


            }

        })

    }
}

//
//        etReservationDate = findViewById(R.id.etReservationDate)
//        etTrainName = findViewById(R.id.etTrainName)
//        etCoach = findViewById(R.id.etCoach)
//        etSeatCat = findViewById(R.id.etSeatCat)
//        etSeats = findViewById(R.id.etSeats)
//        etFrom = findViewById(R.id.etFrom)
//        etTo = findViewById(R.id.etTo)
//        etArrival = findViewById(R.id.etArrival)
//        etReach = findViewById(R.id.etReach)
////        etStatus = findViewById(R.id.etStatus)
//        btnSave = findViewById(R.id.btnSave)
//        btnClear = findViewById(R.id.btnClear)
//
//        btnSave.setOnClickListener {
//
//            saveHistory()
//        }
//
//        btnClear.setOnClickListener {
//            etReservationDate.text.clear()
//            etTrainName.text.clear()
//            etCoach.text.clear()
//            etSeatCat.text.clear()
//            etSeats.text.clear()
//            etFrom.text.clear()
//            etTo.text.clear()
//            etArrival.text.clear()
//            etReach.text.clear()
//
//        }
//
//    }
//
//    private fun saveHistory() {
//
//        val reservationDate = etReservationDate.text.toString().trim()
//        val trainName = etTrainName.text.toString().trim()
//        val  coach = etCoach.text.toString().trim()
//        val seatCat = etSeatCat.text.toString().trim()
//        val seats = etSeats.text.toString().trim()
//        val fromStation = etFrom.text.toString().trim()
//        val nextStation = etTo.text.toString().trim()
//        val arriveTime = etArrival.text.toString().trim()
//        val reachTime = etReach.text.toString().trim()
//
//
//        val myRef = FirebaseDatabase.getInstance().getReference("Transactions")
//
//        val transactionID = myRef.push().key.toString()
//
//        val ph = PH(transactionID, reservationDate, trainName, coach, seatCat, seats, fromStation, nextStation, arriveTime, reachTime)
//
//        myRef.child(transactionID).setValue(ph).addOnCompleteListener {
//
//
//            Toast.makeText(applicationContext, "Details saved successfully", Toast.LENGTH_LONG)
//                .show()
//
//        }



