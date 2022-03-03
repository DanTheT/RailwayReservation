package com.example.railwayreservation.passenger

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.io.*
import java.util.*

class PassengerReservation : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnPickDate: Button
    private lateinit var dateTV: TextView
    private lateinit var btnProceed: Button
    private lateinit var selectionBtn: Button
    private lateinit var originSpinner: Spinner
    private lateinit var destinationSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_reservation)

        datePicker = findViewById(R.id.date_picker)
        dateTV = findViewById(R.id.dateTV)
        btnPickDate = findViewById(R.id.btnPickDate)
        btnProceed = findViewById(R.id.btnProceed)
        selectionBtn = findViewById(R.id.selection_btn)
        originSpinner = findViewById(R.id.origin_spinner)
        destinationSpinner = findViewById(R.id.destination_spinner)

        preloadOrigin()
        preloadDestination()



//        var intent = intent
//        val date = intent.getStringExtra("Reservation Date" )
//        val trainName = intent.getStringExtra("Train Name : ")
//
//        val reservationDateTV = findViewById<TextView>(R.id.reservationDateTV)
//        val trainNameTV = findViewById<TextView>(R.id.trainNameTV)
//
//        reservationDateTV.text = "Reservation Date" + date
//        trainNameTV.text = "Train Name : $trainName"

        // disable dates before today
        val today = Calendar.getInstance()
        val twoDaysLater = today.clone() as Calendar
        twoDaysLater.add(Calendar.DATE, 1)
//        val now = today.timeInMillis
//        datePicker.setMinDate(now)
        datePicker.minDate = twoDaysLater.timeInMillis


        //disable dates after one month later
        val halfYearLater = today.clone() as Calendar
        halfYearLater.add(Calendar.DATE, 30)
        datePicker.maxDate = halfYearLater.timeInMillis

        btnPickDate.setOnClickListener(View.OnClickListener {
            dateTV.text =
                ("Your selected date : " + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear())
        })

        selectionBtn.setOnClickListener {
            val origin: String = originSpinner.toString()
            val destination: String = destinationSpinner.toString()



            if (originSpinner.isEmpty() || destinationSpinner.isEmpty()) {
                Toast.makeText(this, "Please select an origin/destination", Toast.LENGTH_SHORT).show()

            } else {

            }


        }


    }

    private fun preloadOrigin(){
        val lists = resources.getStringArray(R.array.origin_items)

        val listAdapter = ArrayAdapter(this, R.layout.list_for_dropdown, lists)
        originSpinner.setAdapter(listAdapter)
    }

    private fun preloadDestination() {
        val lists = resources.getStringArray(R.array.destination_items)

        val listAdapter = ArrayAdapter(this, R.layout.list_for_dropdown, lists)
        destinationSpinner.setAdapter(listAdapter)
    }
}


