package com.example.railwayreservation.passenger


class Ticket (
    val id: String,
    val reservationDate: String,
    val seatCategory: String,
    val totalAmount: String
) {
    constructor() : this ("", "", "", ""){
    }
}
