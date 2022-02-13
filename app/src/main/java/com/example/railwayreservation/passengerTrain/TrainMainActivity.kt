package com.example.railwayreservation.passengerTrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityTrainMainBinding
import com.example.railwayreservation.passenger.SchedulelistActivity
import com.example.railwayreservation.passengerTrain.trainInfo.TrainInfoFragment

class TrainMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}