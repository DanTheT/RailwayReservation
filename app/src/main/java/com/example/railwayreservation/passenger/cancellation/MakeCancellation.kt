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
    private lateinit var etOrigin: EditText
    private lateinit var etDestination: EditText
    private lateinit var etArrival: EditText
    private lateinit var etReach: EditText
    private lateinit var etSeatCategory: EditText
    private lateinit var etTotalAmount: EditText

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



        //view cancellation
        cancellationList = mutableListOf()

        val myRef = FirebaseDatabase.getInstance().getReference("Reservations")

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
                    CancellationAdapter(
                        this@MakeCancellation,
                        R.layout.cancellation,
                        cancellationList
                    )
                cancellationListView.adapter = adapter


            }

        })

    }


//    fun saveCancellation() {
//
//        val reservationDate = etReservationDate.text.toString().trim()
//        val trainName = etTrainName.text.toString().trim()
//        val coach = etCoach.text.toString().trim()
//        val origin = etOrigin.text.toString().trim()
//        val destination = etDestination.text.toString().trim()
//        val arriveTime = etArrival.text.toString().trim()
//        val reachTime = etReach.text.toString().trim()
//        val seatCategory = etSeatCategory.text.toString().trim()
//        val totalAmount = etTotalAmount.text.toString().trim()
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
//            origin,
//            destination,
//            arriveTime,
//            reachTime,
//            seatCategory,
//            totalAmount
//        )
//
//        myRef.child(transactionID).setValue(cancellation).addOnCompleteListener {
//
//
//            Toast.makeText(applicationContext, "Cancellation has been made", Toast.LENGTH_LONG)
//                .show()
//
//        }
//    }
}



