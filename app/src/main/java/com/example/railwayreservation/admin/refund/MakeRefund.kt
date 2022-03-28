package com.example.railwayreservation.admin.refund

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.cancellation.Cancellation
import com.example.railwayreservation.passenger.cancellation.CancellationAdapter
import com.google.firebase.database.*

class MakeRefund : AppCompatActivity() {


    private lateinit var refundList: MutableList<Refund>
    private lateinit var refundListView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_refund)

        //view refund
        refundList = mutableListOf()

        val myRef = FirebaseDatabase.getInstance().getReference("Tickets")

        refundListView = findViewById(R.id.refundListView)

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {

                    for (h in snapshot.children) {
                        val refund = h.getValue(Refund::class.java)
                        refundList.add((refund!!))

                    }
                }

                val adapter =
                    RefundAdapter(this@MakeRefund, R.layout.refund, refundList)
                refundListView.adapter = adapter


            }

        })



    }
}