package com.example.railwayreservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.railwayreservation.admin.AdminLogin
import com.example.railwayreservation.databinding.ActivityMainBinding
import com.example.railwayreservation.passenger.PassengerHome
import com.example.railwayreservation.passenger.PassengerLogin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonPassengerLogin.setOnClickListener {
            val intent = Intent(this, PassengerLogin::class.java).apply {

            }
            startActivity(intent)
        }

        binding.buttonAdminLogin.setOnClickListener {
            val intent = Intent(this, AdminLogin::class.java).apply {

            }
            startActivity(intent)
        }
    }
}