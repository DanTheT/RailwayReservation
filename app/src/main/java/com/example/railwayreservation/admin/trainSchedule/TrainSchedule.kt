package com.example.railwayreservation.admin.trainSchedule

data class TrainSchedule(
    val fromStation: String? = "",
    val nextStation: String? = "",
    val arrivalTime: String? = "",
    val departureTime: String? = ""
)
