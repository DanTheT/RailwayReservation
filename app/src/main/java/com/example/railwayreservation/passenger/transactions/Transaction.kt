package com.example.railwayreservation.passenger.transactions


class Transaction (
    val id: String,
    val reservationDate: String,
    val trainName: String,
    val coach: String,
    val seatCat: String,
    val seats: String,
    val fromStation: String,
    val nextStation: String,
    val arriveTime: String,
    val reachTime: String
) {
    constructor() : this ("", "", "", "", "", "", "", "", "", ""){
    }
}
