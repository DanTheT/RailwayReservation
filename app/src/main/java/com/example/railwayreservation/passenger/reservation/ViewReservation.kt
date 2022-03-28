package com.example.railwayreservation.passenger.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.railwayreservation.R
import com.google.firebase.database.*

class ViewReservation : AppCompatActivity() {

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
        setContentView(R.layout.activity_view_reservation)

        //view reservation
        reservationList = mutableListOf()

        val myRef = FirebaseDatabase.getInstance().getReference("Reservations")

        reservationListView = findViewById(R.id.reservationListView)

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {

                    for (h in snapshot.children) {
                        val reservation = h.getValue(Reservation::class.java)
                        reservationList.add((reservation!!))

                    }
                }

                val adapter =
                    ReservationAdapter(this@ViewReservation, R.layout.reservation, reservationList)
                reservationListView.adapter = adapter


            }

        })

    }
}




