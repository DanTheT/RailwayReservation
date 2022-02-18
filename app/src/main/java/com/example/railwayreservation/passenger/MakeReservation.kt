package com.example.railwayreservation.passenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityAdminLoginBinding.inflate
import com.example.railwayreservation.databinding.ActivityMainBinding.inflate

class MakeReservation : AppCompatActivity() {

    private lateinit var reserveTrainNameSpinner : AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservation)

//        reserveTrainNameSpinner = findViewById(R.id.reserveTrainNameSpinner)
//        adapterItems = newArrayAdapter<String>(R.layout.list_for_dropdown, trainName)
//        reserveTrainNameSpinner.setAdapter(adapterItems)
//
//        val trainName = resources.getStringArray(R.array.trainName)
//        val arrayAdapter = ArrayAdapter(requiredContext(),R.layout.list_for_dropdown, trainName)
//        reserveTrainNameSpinner.setAdapter(arrayAdapter)

    }
}


