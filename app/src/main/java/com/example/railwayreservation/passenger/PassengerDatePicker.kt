package com.example.railwayreservation.passenger

import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.cancellation.MakeCancellation
import com.example.railwayreservation.passengerTrain.TrainMainActivity
import com.example.railwayreservation.passengerTrain.trainInfo.TrainInfoFragment
import java.util.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo

class PassengerDatePicker : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnPickDate: Button
    private lateinit var dateTV: TextView
    private lateinit var btnProceed: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_date_picker)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = TrainInfoFragment()

        datePicker = findViewById(R.id.date_picker)
        dateTV = findViewById(R.id.dateTV)
        btnPickDate = findViewById(R.id.btnPickDate)
        btnProceed = findViewById(R.id.btnProceed)


        // disable dates 1 day before today's date
        val today = Calendar.getInstance()
        val oneDayLater = today.clone() as Calendar
        oneDayLater.add(Calendar.DATE, 1)
        datePicker.minDate = oneDayLater.timeInMillis

        //disable dates after one month later
        val halfYearLater = today.clone() as Calendar
        halfYearLater.add(Calendar.DATE, 30)
        datePicker.maxDate = halfYearLater.timeInMillis

        btnPickDate.setOnClickListener(View.OnClickListener {
            dateTV.text =
                ("Your selected date : " + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear())
        })


        btnProceed.setOnClickListener {


//            val fragment = TrainInfoFragment()
//            val bundle = Bundle()
//            bundle.putString("date", dateTV.text.toString())
//            fragment.arguments = bundle
//            supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()


            if(dateTV.text.isNotEmpty()) {
                startActivity(Intent(this, TrainMainActivity::class.java))

            }
            else {
                Toast.makeText(this, "No date is selected.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}