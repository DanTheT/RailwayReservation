package com.example.railwayreservation.passenger.cancellation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.reservation.Reservation
import com.example.railwayreservation.passenger.reservation.ReservationAdapter
import com.google.firebase.database.*
import org.w3c.dom.Text

class MakeCancellation : AppCompatActivity() {


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
    private lateinit var btnRequest: Button
    private lateinit var statusTV: TextView



    private lateinit var myRef: DatabaseReference
    private lateinit var cancellationList: MutableList<Cancellation>
    private lateinit var cancellationListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_cancellation)


//        statusTV = findViewById(R.id. statusTV)
//        btnRequest = findViewById(R.id.btnRequest)
//        btnRequest.setOnClickListener{
//            btnRequest.isEnabled = false
//            statusTV.text = "Pending"
//        }

        //view cancellation
        cancellationList = mutableListOf()

        val myRef = FirebaseDatabase.getInstance().getReference("Tickets")

        cancellationListView = findViewById(R.id.cancellationListView)

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {

                    for (h in snapshot.children) {
                        val cancellation = h.getValue(Cancellation::class.java)
                        cancellationList.add((cancellation!!))

                    }
                }

                val adapter =
                    CancellationAdapter(this@MakeCancellation, R.layout.cancellation, cancellationList)
                cancellationListView.adapter = adapter


            }

        })

    }
}

//        //save cancellation records

//            etSeatCategory = findViewById(R.id.etSeatCategory)
//            etTotalAmount = findViewById(R.id.etTotalAmount)

//        etReservationDate = findViewById(R.id.etReservationDate)
//        etTrainName = findViewById(R.id.etTrainName)
//        etCoach = findViewById(R.id.etCoach)
//        etSeatCat = findViewById(R.id.etSeatCat)
//        etSeats = findViewById(R.id.etSeats)
//        etSeatPrice = findViewById(R.id.etSeatPrice)
//        etFrom = findViewById(R.id.etFrom)
//        etTo = findViewById(R.id.etTo)
//        etArrival = findViewById(R.id.etArrival)
//        etReach = findViewById(R.id.etReach)
//        btnSave = findViewById(R.id.btnSave)
//        btnClear = findViewById(R.id.btnClear)
//
//        btnSave.setOnClickListener {
//
//            saveCancellation()
//        }
//
//        btnClear.setOnClickListener {
//            etReservationDate.text.clear()
//            etTrainName.text.clear()
//            etCoach.text.clear()
//            etSeatCat.text.clear()
//            etSeats.text.clear()
//            etSeatPrice.text.clear()
//            etFrom.text.clear()
//            etTo.text.clear()
//            etArrival.text.clear()
//            etReach.text.clear()
//
//        }
//    }
//
//    fun saveCancellation() {
//
//
//        val reservationDate = etReservationDate.text.toString().trim()
//        val trainName = etTrainName.text.toString().trim()
//        val coach = etCoach.text.toString().trim()
//        val seatCat = etSeatCat.text.toString().trim()
//        val seats = etSeats.text.toString().trim()
//        val seatPrice = etSeatPrice.text.toString().trim()
//        val fromStation = etFrom.text.toString().trim()
//        val nextStation = etTo.text.toString().trim()
//        val arriveTime = etArrival.text.toString().trim()
//        val reachTime = etReach.text.toString().trim()
//
//
//        val myRef = FirebaseDatabase.getInstance().getReference("Cancellation")
//
//        val transactionID = myRef.push().key.toString()
//
//        val cancellation = Cancellation(
//            transactionID,
//            reservationDate,
//            trainName,
//            coach,
//            seatCat,
//            seats,
//            seatPrice,
//            fromStation,
//            nextStation,
//            arriveTime,
//            reachTime
//        )
//
//        myRef.child(transactionID).setValue(cancellation).addOnCompleteListener {
//
//
//            Toast.makeText(applicationContext, "Details saved successfully", Toast.LENGTH_LONG)
//                .show()
//
//        }
//    }
//}

