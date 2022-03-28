package com.example.railwayreservation.passenger


class Reservation (
    val id: String,
    val reservationDate: String,
    val trainName: String,
    val coach: String,
    val origin: String,
    val destination: String,
    val arriveTime: String,
    val reachTime: String
) {
    constructor() : this ("", "", "", "", "", "", "", ""){
    }
}
