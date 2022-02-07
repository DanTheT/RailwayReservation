package com.example.railwayreservation.admin.trainSchedule

data class Schedule(
    val trainName: String = "",
    val fromStation: String = "",
    val arriveTime:  String = "",
    val nextStation: String = "",
    val reachTime: String = ""
)
