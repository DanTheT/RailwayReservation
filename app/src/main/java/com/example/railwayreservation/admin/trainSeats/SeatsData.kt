package com.example.railwayreservation.admin.trainSeats

data class SeatsData(
    val available: String = "",
    val seatNo: ArrayList<String> ,
    val reserved: String = "",
    val seatPrice: String = ""
)
