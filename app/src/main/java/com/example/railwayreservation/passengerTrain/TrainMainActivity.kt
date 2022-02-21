package com.example.railwayreservation.passengerTrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.railwayreservation.databinding.ActivityTrainMainBinding

class TrainMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}