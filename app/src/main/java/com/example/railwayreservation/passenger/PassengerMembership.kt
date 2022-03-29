package com.example.railwayreservation.passenger

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.DateFormat
import java.util.*

class PassengerMembership : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private lateinit var statusTV: TextView
    private lateinit var sinceDateTV: TextView

    private lateinit var btnApplyMembership: Button
    private lateinit var btnclaim1: Button
    private lateinit var btnclaim2: Button
    private lateinit var btnclaim3: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_membership)

        db = FirebaseDatabase.getInstance()
        myRef = db!!.reference!!.child("User")
        auth = FirebaseAuth.getInstance()

        statusTV = findViewById(R.id.StatusView)
        sinceDateTV = findViewById(R.id.sinceDateView)

        btnApplyMembership = findViewById(R.id.btnApplyMembership)
        btnclaim1 = findViewById(R.id.btnclaim1)
        btnclaim2 = findViewById(R.id.btnclaim2)
        btnclaim3 = findViewById(R.id.btnclaim3)


        loadProfile()

        btnApplyMembership.setOnClickListener {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Expired ? Apply for new membership ! ")
                val view = layoutInflater.inflate(R.layout.applynewmembership_dialog, null)

                builder.setView(view)
                builder.setPositiveButton("Apply", DialogInterface.OnClickListener { _, _ ->
                    applyNewMembership()
                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()

            btnclaim1.setOnClickListener {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Membership")
                val view = layoutInflater.inflate(R.layout.claim1, null)

                builder.setView(view)
                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }

            btnclaim2.setOnClickListener {

                btnclaim2.isEnabled = false

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Few more days to go!")
                val view = layoutInflater.inflate(R.layout.claim2, null)

                builder.setView(view)
                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }

            btnclaim3.setOnClickListener {

                btnclaim3.isEnabled = false

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Few more days to go!")
                val view = layoutInflater.inflate(R.layout.claim3, null)


                builder.setView(view)
                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }
        }

            }


        private fun applyNewMembership() {

            val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

            val applyNewURL = Intent(Intent.ACTION_VIEW)
            applyNewURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
            startActivity(applyNewURL)

            statusTV.text = "Active"
            sinceDateTV.text = currentDateTimeString

        }



    private fun loadProfile() {
        val user = auth.currentUser
        val userRef = myRef.child(user?.uid!!)


        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                statusTV.text = snapshot.child("status").value.toString().trim()
                sinceDateTV.text = snapshot.child("since").value.toString().trim()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}
