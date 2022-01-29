package com.example.railwayreservation.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.railwayreservation.databinding.ActivityAdminLoginBinding

class AdminLogin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
