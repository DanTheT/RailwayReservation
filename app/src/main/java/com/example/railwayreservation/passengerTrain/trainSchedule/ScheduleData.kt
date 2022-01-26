package com.example.railwayreservation.passengerTrain.trainSchedule

data class ScheduleData(
    val fromStation: String? = "",
    val nextStation: String? = "",
    val arrivalTime: String? = "",
    val departureTime: String? = "",
)
