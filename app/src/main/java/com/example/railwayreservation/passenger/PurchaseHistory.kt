package com.example.railwayreservation.passenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.renderscript.Sampler
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PurchaseHistory : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var transactionRecyclerView : RecyclerView
    private lateinit var transactionArrayList: ArrayList<Transactions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_history)

        transactionRecyclerView = findViewById(R.id.historyList)
        transactionRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionRecyclerView.setHasFixedSize(true)

        transactionArrayList = arrayListOf<Transactions>()
        getTransactionData()

    }

    private fun getTransactionData() {

        myRef = FirebaseDatabase.getInstance().getReference("Transactions")

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()) {
                    for (transactionSnapshot in snapshot.children) {

                        val transaction = transactionSnapshot.getValue(Transactions::class.java)
                        transactionArrayList.add(transaction!!)
                    }

                    transactionRecyclerView.adapter = TransactionAdapter(transactionArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    }