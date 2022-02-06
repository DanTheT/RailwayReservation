package com.example.railwayreservation.admin.trainInfo.updateTrain

import androidx.lifecycle.ViewModel
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateTrainViewModel: ViewModel() {
    private lateinit var trainDatabase: DatabaseReference

    fun updateTrain(trainName: String, trainInfo: TrainInfo) {
        trainDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
        trainDatabase.child(trainName).setValue(trainInfo)
    }
}