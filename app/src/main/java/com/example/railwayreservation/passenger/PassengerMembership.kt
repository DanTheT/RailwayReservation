package com.example.railwayreservation.passenger

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

            btn200points.setOnClickListener {

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Enough points. Applicable to claim 20% discount. ")
                val view = layoutInflater.inflate(R.layout.claim200points_dialog, null)

                builder.setView(view)
                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
                    claim200points()
                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }

            btn400points.setOnClickListener {

//                btn400points.isEnabled = false

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Not enough points. Not applicable to claim 40% discount. ")
                val view = layoutInflater.inflate(R.layout.claim400points_dialog, null)

//                builder.setView(view)
//                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
//                    claim400points()
//                })

                builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
                builder.show()
            }

            btn800points.setOnClickListener {

//                btn800points.isEnabled = false

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Not enough points. Not applicable to claim 80% discount. ")
                val view = layoutInflater.inflate(R.layout.claim800points_dialog, null)


//                builder.setView(view)
//                builder.setPositiveButton("Claim", DialogInterface.OnClickListener { _, _ ->
//                    claim800points()
//                })

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


    private fun claim200points() {

        val applyNewURL = Intent(Intent.ACTION_VIEW)
        applyNewURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
        startActivity(applyNewURL)

        pointsTV.text = "2"

    }

    private fun claim400points() {

        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

        val applyNewURL = Intent(Intent.ACTION_VIEW)
        applyNewURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
        startActivity(applyNewURL)


    }

    private fun claim800points() {

        val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

        val applyNewURL = Intent(Intent.ACTION_VIEW)
        applyNewURL.data = Uri.parse("https://www.sandbox.paypal.com/myaccount/transfer/homepage/buy/preview")
        startActivity(applyNewURL)

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
