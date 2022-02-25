package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.railwayreservation.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PassengerAccount : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private lateinit var nameTV: TextView
    private lateinit var emailTV: TextView
    private lateinit var phoneTV: TextView


    private lateinit var btnLogout: Button
    private lateinit var btnChangePw: Button
    private lateinit var btnFAQ: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_account)

//get a user's profile
        db = FirebaseDatabase.getInstance()
        myRef = db!!.reference!!.child("User")
        auth = FirebaseAuth.getInstance()

        btnChangePw = findViewById(R.id.btnChangePw)
        btnChangePw.setOnClickListener {
            startActivity(Intent(this, PassengerChgPw::class.java))
            finish()
        }

        btnLogout = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            auth.signOut()
            startActivity(Intent(this, PassengerScreen::class.java))
            finish()
        }

        btnFAQ = findViewById(R.id.btnFAQ)
        btnFAQ.setOnClickListener {
            startActivity(Intent(this, PassengerFAQ::class.java))

        }

        nameTV = findViewById(R.id.NameView)
        emailTV = findViewById(R.id.EmailView)
        phoneTV = findViewById(R.id.PhoneView)

        loadProfile()
    }

    private fun loadProfile() {
        val user = auth.currentUser
        val userRef = myRef.child(user?.uid!!)


        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                emailTV.text = user.email
                nameTV.text = snapshot.child("name").value.toString().trim()
//                emailTV.text = snapshot.child(" email").value.toString().trim()
                phoneTV.text = snapshot.child("phone").value.toString().trim()


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}



