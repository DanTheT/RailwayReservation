package com.example.railwayreservation.passenger

data class Schedule(
    val trainName: String = "",
    val fromStation: String = "",
    val arriveTime:  String = "",
    val nextStation: String = "",
    val reachTime: String = "",
    val status: String = ""
)
