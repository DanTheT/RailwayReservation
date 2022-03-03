package com.example.railwayreservation.passenger.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.transactions.TransactionAdapter
import com.google.firebase.database.*

class MakeReservation : AppCompatActivity() {

    private lateinit var etReservationDate: EditText
    private lateinit var etTrainName: EditText
    private lateinit var etCoach: EditText
    private lateinit var etSeatCat: EditText
    private lateinit var etSeats: EditText
    private lateinit var etSeatPrice: EditText
    private lateinit var etFrom: EditText
    private lateinit var etTo: EditText
    private lateinit var etArrival: EditText
    private lateinit var etReach: EditText

    private lateinit var btnSave: Button
    private lateinit var btnClear: Button

    private lateinit var myRef: DatabaseReference
    private lateinit var reservationList: MutableList<Reservation>
    private lateinit var reservationListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservation)
    }
}


//            //view reservation
//            reservationList = mutableListOf()
//
//            val myRef = FirebaseDatabase.getInstance().getReference("Reservations")
//
//            reservationListView = findViewById(R.id.reservationListView)
//
//            myRef.addValueEventListener(object : ValueEventListener {
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot!!.exists()) {
//
//                        for (h in snapshot.children) {
//                            val reservation = h.getValue(Reservation::class.java)
//                            reservationList.add((reservation!!))
//
//                        }
//                    }
//
//                    val adapter =
//                        ReservationAdapter(this@MakeReservation, R.layout.reservation, reservationList)
//                    reservationListView.adapter = adapter
//
//
//                }
//
//            })
//
//        }
//    }


//            //save reservation records
//            etReservationDate = findViewById(R.id.etReservationDate)
//            etTrainName = findViewById(R.id.etTrainName)
//            etCoach = findViewById(R.id.etCoach)
//            etSeatCat = findViewById(R.id.etSeatCat)
//            etSeats = findViewById(R.id.etSeats)
//            etSeatPrice = findViewById(R.id.etSeatPrice)
//            etFrom = findViewById(R.id.etFrom)
//            etTo = findViewById(R.id.etTo)
//            etArrival = findViewById(R.id.etArrival)
//            etReach = findViewById(R.id.etReach)
//            btnSave = findViewById(R.id.btnSave)
//            btnClear = findViewById(R.id.btnClear)
//
//            btnSave.setOnClickListener {
//
//                saveReservation()
//            }
//
//            btnClear.setOnClickListener {
//                etReservationDate.text.clear()
//                etTrainName.text.clear()
//                etCoach.text.clear()
//                etSeatCat.text.clear()
//                etSeats.text.clear()
//                etSeatPrice.text.clear()
//                etFrom.text.clear()
//                etTo.text.clear()
//                etArrival.text.clear()
//                etReach.text.clear()
//
//            }
//        }
//
//        fun saveReservation() {
//
//
//            val reservationDate = etReservationDate.text.toString().trim()
//            val trainName = etTrainName.text.toString().trim()
//            val coach = etCoach.text.toString().trim()
//            val seatCat = etSeatCat.text.toString().trim()
//            val seats = etSeats.text.toString().trim()
//            val seatPrice = etSeatPrice.text.toString().trim()
//            val fromStation = etFrom.text.toString().trim()
//            val nextStation = etTo.text.toString().trim()
//            val arriveTime = etArrival.text.toString().trim()
//            val reachTime = etReach.text.toString().trim()
//
//
//            val myRef = FirebaseDatabase.getInstance().getReference("Reservations")
//
//            val transactionID = myRef.push().key.toString()
//
//            val reservation = Reservation(
//                transactionID,
//                reservationDate,
//                trainName,
//                coach,
//                seatCat,
//                seats,
//                seatPrice,
//                fromStation,
//                nextStation,
//                arriveTime,
//                reachTime
//            )
//
//            myRef.child(transactionID).setValue(reservation).addOnCompleteListener {
//
//
//                Toast.makeText(applicationContext, "Details saved successfully", Toast.LENGTH_LONG)
//                    .show()
//
//            }
//        }
//    }



