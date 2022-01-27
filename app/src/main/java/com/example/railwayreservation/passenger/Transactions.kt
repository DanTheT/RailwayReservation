package com.example.railwayreservation.passenger

data class Transactions(
    val arrivalTime: String ?= "",
    val date : String ?= "",
    val departTime : String ?= "",
    val fromStation : String ?= "",
    val reachTime : String ?= "",
    val toStation : String ?= "",
    val transactionID : String ?= "" )

//val transactionID : Long ?= null )

