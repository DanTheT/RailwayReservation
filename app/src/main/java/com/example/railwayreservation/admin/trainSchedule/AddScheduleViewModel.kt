package com.example.railwayreservation.admin.trainSchedule

import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class AddScheduleViewModel: ViewModel() {
    private lateinit var scheduleDatabase: DatabaseReference
    val nameArrayList = arrayListOf<String>()

    fun insertNewSchedule(trainName: String, arriveTime: String, schedule: Schedule) {
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")
        scheduleDatabase.child(trainName).child(arriveTime).setValue(schedule)
    }

    fun getTrainName() {

    }
}