package com.example.railwayreservation.passenger

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.railwayreservation.R
import com.example.railwayreservation.passengerTrain.TrainMainActivity
import java.util.*

class PassengerDatePicker : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnPickDate: Button
    private lateinit var dateTV: TextView
    private lateinit var btnProceed: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_date_picker)

        datePicker = findViewById(R.id.date_picker)
        dateTV = findViewById(R.id.dateTV)
        btnPickDate = findViewById(R.id.btnPickDate)
        btnProceed = findViewById(R.id.btnProceed)


        // disable dates before today
        val today = Calendar.getInstance()
        val twoDaysLater = today.clone() as Calendar
        twoDaysLater.add(Calendar.DATE, 2)
//        val now = today.timeInMillis
//        datePicker.setMinDate(now)
        datePicker.minDate = twoDaysLater.timeInMillis


        //disable dates after half year later
        val halfYearLater = today.clone() as Calendar
        halfYearLater.add(Calendar.DATE, 183)
        datePicker.maxDate = halfYearLater.timeInMillis

        btnPickDate.setOnClickListener(View.OnClickListener {
            dateTV.text =
                ("Your selected date : " + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear())
        })

//        btnSave.setOnClickListener {
//            val date = dateTV.text.toString()
//
//            if (dateTV.text.isEmpty()) {
//                Toast.makeText(this, "No date is selected.", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                val intent = Intent(this, PassengerReservation::class.java)
//                intent.putExtra("Reservation Date:", date)
//            }
//        }

        btnProceed.setOnClickListener {

            val date = dateTV.text.toString()


            if (dateTV.text.isEmpty()) {
                Toast.makeText(this, "No date is selected.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, PassengerReservation::class.java)
                intent.putExtra("Reservation Date", date)
                startActivity(Intent(this, TicketActivity::class.java))
            }

        }
    }
}