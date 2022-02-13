package com.example.railwayreservation.admin.trainSchedule

import androidx.lifecycle.ViewModel
import com.example.railwayreservation.admin.trainSchedule.data.Schedule
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateScheduleViewModel: ViewModel() {
    private lateinit var scheduleDatabase: DatabaseReference

    fun updateSchedule(trainName: String, arriveTime: String, schedule: Schedule) {
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")
        scheduleDatabase.child(trainName).child(arriveTime).setValue(schedule)
    }
}