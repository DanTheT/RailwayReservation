package com.example.railwayreservation.passenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PassengerMembership : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private lateinit var statusTV: TextView
    private lateinit var sinceDateTV: TextView
    private lateinit var pointsTV: TextView


    private lateinit var btnApplyMembership: Button
    private lateinit var btn200points: Button
    private lateinit var btn400points: Button
    private lateinit var btn800points: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_membership)

        db = FirebaseDatabase.getInstance()
        myRef = db!!.reference!!.child("User")
        auth = FirebaseAuth.getInstance()

        statusTV = findViewById(R.id.StatusView)
        sinceDateTV = findViewById(R.id.sinceDateView)
        pointsTV = findViewById(R.id.pointsView)

        btnApplyMembership = findViewById(R.id.btnApplyMembership)
        btn200points = findViewById(R.id.btn200points)
        btn400points = findViewById(R.id.btn400points)
        btn800points = findViewById(R.id.btn800points)

//        btnApplyMembership.isEnabled = false
//        btn200points.isEnabled = false
//        btn400points.isEnabled = false
//        btn800points.isEnabled = false


        loadProfile()
    }



    private fun loadProfile() {
        val user = auth.currentUser
        val userRef = myRef.child(user?.uid!!)


        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                statusTV.text = snapshot.child("status").value.toString().trim()
                sinceDateTV.text = snapshot.child("since").value.toString().trim()
                pointsTV.text = snapshot.child("points").value.toString().trim()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}
