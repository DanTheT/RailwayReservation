package com.example.railwayreservation.passenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.core.view.isEmpty
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.example.railwayreservation.passengerTrain.trainInfo.TrainInfoFragmentDirections
import com.example.railwayreservation.passengerTrain.trainInfo.TrainName
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.*
import java.util.*

class PassengerReservation : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_reservation)



        var intent = intent
        val date = intent.getStringExtra("Reservation Date" )
        val trainName = intent.getStringExtra("Train Name : ")

        val reservationDateTV = findViewById<TextView>(R.id.reservationDateTV)
        val trainNameTV = findViewById<TextView>(R.id.trainNameTV)

        reservationDateTV.text = "Reservation Date" + date
        trainNameTV.text = "Train Name : $trainName"


    }
}


