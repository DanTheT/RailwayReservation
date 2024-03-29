package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.refund.Refund
import com.example.railwayreservation.databinding.ActivityPassengerHomeBinding
import com.example.railwayreservation.databinding.ReservationBinding
import com.example.railwayreservation.passenger.cancellation.MakeCancellation
import com.example.railwayreservation.passengerTrain.TrainMainActivity
import com.example.railwayreservation.reportIssue.ReportIssue
import com.example.railwayreservation.reportIssue.updates.MessageUI
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.auth.FirebaseAuth

class PassengerHome : AppCompatActivity() {
    private lateinit var binding: ActivityPassengerHomeBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var  btnReservation: ImageButton
    private lateinit var  btnCancellation: ImageButton
    private lateinit var  btnPayment: ImageButton
    private lateinit var  btnTicket: ImageButton
    private lateinit var  btnAccount: ImageButton
    private lateinit var  btnReport: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassengerHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, PassengerLogin::class.java)
            startActivity(intent)
            finish()
        }

//        //to change title of activity
//        val actionBar = supportActionBar
//        actionBar!!.title = "Home"
//
//        //to set back button
//        actionBar.setDisplayHomeAsUpEnabled(true)

        //go to refund policy screen
        btnReservation = findViewById(R.id.btnReservation)
        btnReservation.setOnClickListener {
            startActivity(Intent(this,RefundPolicy::class.java))
            }

        //go to cancellation screen
        btnCancellation = findViewById(R.id.btnCancellation)
        btnCancellation.setOnClickListener {
            startActivity(Intent(this, MakeCancellation::class.java))
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
        btnReport = findViewById(R.id.btnMembership)
        btnReport.setOnClickListener{
            startActivity(Intent(this, PassengerMembership::class.java))
        }


        binding.passengerHomeTopAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuIssues -> {
                    startActivity(Intent(this, ReportIssue::class.java))
                    true
                }
                R.id.menuIssueMessages -> {
                    startActivity(Intent(this, MessageUI::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

}


