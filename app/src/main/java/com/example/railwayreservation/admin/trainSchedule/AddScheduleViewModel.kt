package com.example.railwayreservation.admin.trainSchedule

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddScheduleViewModel: ViewModel() {
    private lateinit var scheduleDatabase: DatabaseReference

    fun insertNewSchedule(trainName: String, arriveTime: String, schedule: Schedule) {
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")
        scheduleDatabase.child(trainName).child(arriveTime).setValue(schedule)
    }

    fun getTrainName() {
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")
        scheduleDatabase.get()
    }
}