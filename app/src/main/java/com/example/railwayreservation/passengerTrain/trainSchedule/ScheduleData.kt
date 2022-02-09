package com.example.railwayreservation.passengerTrain.trainSchedule

data class ScheduleData(
    val trainName: String = "",
    val fromStation: String = "",
    val arriveTime:  String = "",
    val nextStation: String = "",
    val reachTime: String = ""
)
