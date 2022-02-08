package com.example.railwayreservation.admin.trainSeats.addSeats

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddSeatsViewModel: ViewModel() {
    private lateinit var getInfoDatabase: DatabaseReference

    fun getTrainRef() {
        getInfoDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
    }
}