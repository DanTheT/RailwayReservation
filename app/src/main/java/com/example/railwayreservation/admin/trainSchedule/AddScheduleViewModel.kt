package com.example.railwayreservation.admin.trainSchedule

import androidx.lifecycle.ViewModel
import com.example.railwayreservation.admin.trainSchedule.data.Schedule
import com.google.firebase.database.*

class AddScheduleViewModel: ViewModel() {
    private lateinit var scheduleDatabase: DatabaseReference

    fun insertNewSchedule(trainName: String, arriveTime: String, schedule: Schedule) {
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")
        scheduleDatabase.child(trainName).child(arriveTime).setValue(schedule)
    }
}