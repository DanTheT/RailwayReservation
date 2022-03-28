package com.example.railwayreservation.admin.refund


class Refund (
    val id: String,
    val reservationDate: String,
    val seatCategory: String,
    val totalAmount: String
) {
    constructor() : this ("", "", "", ""){
    }
}
