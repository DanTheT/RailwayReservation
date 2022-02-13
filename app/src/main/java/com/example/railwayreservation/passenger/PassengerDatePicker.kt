package com.example.railwayreservation.passenger

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.railwayreservation.R
import java.util.*

class PassengerDatePicker : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnPickDate: Button
    private lateinit var dateTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_date_picker)

        datePicker = findViewById(R.id.date_picker)
        dateTV = findViewById(R.id.dateTV)
        btnPickDate = findViewById(R.id.btnPickDate)

        // disable dates before today
        val today = Calendar.getInstance()
        val now = today.timeInMillis
        datePicker.setMinDate(now)

        val halfYearLater = today.clone() as Calendar
        halfYearLater.add(Calendar.DATE, 183)
        datePicker.maxDate = halfYearLater.timeInMillis


        btnPickDate.setOnClickListener(View.OnClickListener {
            dateTV.text=("   Your Selected Date : " + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear()) })
    }

    
}
