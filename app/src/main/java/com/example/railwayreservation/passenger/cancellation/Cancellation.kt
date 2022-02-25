package com.example.railwayreservation.passenger.cancellation


class Cancellation (
    val id: String,
    val reservationDate: String,
    val trainName: String,
    val coach: String,
    val seatCat: String,
    val seats: String,
    val seatPrice: String,
    val fromStation: String,
    val nextStation: String,
    val arriveTime: String,
    val reachTime: String
) {
    constructor() : this ("", "", "", "", "", "", "", "", "", "", ""){
    }
}
