package com.example.railwayreservation.passenger

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.railwayreservation.R
import java.util.*

class PassengerDatePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var btnPickDate: Button
        lateinit var dateTV: TextView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_date_picker)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        dateTV = findViewById(R.id.dateTV)
        btnPickDate = findViewById(R.id.btnPickDate)

        btnPickDate.setOnClickListener{
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {view, mYear,mMonth , mDay ->
                val mMonth = mMonth+1
                val date = "$mDay/$mMonth/$mYear"
                dateTV.setText(date)
            },year,month,day)
            dpd.show()
        }


        }
    }