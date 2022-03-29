package com.example.railwayreservation.admin.refund


class Refund (
    val id: String,
    val reservationDate: String,
    val trainName: String,
    val coach: String,
    val origin: String,
    val destination: String,
    val arriveTime: String,
    val reachTime: String,
    val seatCategory: String,
    val totalAmount: String
) {
    constructor() : this ("", "", "", "", "", "", "", "", "", ""){
    }
}
