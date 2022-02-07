package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.railwayreservation.R
import com.example.railwayreservation.passengerTrain.TrainMainActivity
import com.example.railwayreservation.reportIssue.ReportIssue
import com.google.firebase.auth.FirebaseAuth

class PassengerHome : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var  btnReservation: ImageButton
    private lateinit var  btnCancellation: ImageButton
    private lateinit var  btnPayment: ImageButton
    private lateinit var  btnTicket: ImageButton
    private lateinit var  btnAccount: ImageButton
    private lateinit var  btnMembership: ImageButton
    private lateinit var btnIssues: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, PassengerLogin::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_passenger_home)

//go to reservation screen
        btnReservation = findViewById(R.id.btnReservation)
        btnReservation.setOnClickListener {
            startActivity(Intent(this,TrainMainActivity::class.java))

            }

        //go to cancellation screen
        btnCancellation = findViewById(R.id.btnCancellation)
        btnCancellation.setOnClickListener {
            startActivity(Intent(this, PassengerCancellation::class.java))
        }

        //go to payment screen
        btnPayment = findViewById(R.id.btnPayment)
        btnPayment.setOnClickListener{
            startActivity(Intent(this, PassengerPayment::class.java))
        }

        //go to ticket screen
        btnTicket = findViewById(R.id.btnTicket)
        btnTicket.setOnClickListener{
            startActivity(Intent(this, PassengerTicket::class.java))
        }

        //go to account screen
        btnAccount = findViewById(R.id.btnAccount)
        btnAccount.setOnClickListener{
            startActivity(Intent(this, PassengerAccount::class.java))
        }

        //go to membership screen
        btnMembership = findViewById(R.id.btnMembership)
        btnMembership.setOnClickListener{
            startActivity(Intent(this, PassengerMembership::class.java))
        }

        btnIssues = findViewById(R.id.btnToIssues)
        btnIssues.setOnClickListener{
            startActivity(Intent(this, ReportIssue::class.java))
        }
    }
}

