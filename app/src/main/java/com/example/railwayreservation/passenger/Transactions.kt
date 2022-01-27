package com.example.railwayreservation.passenger

data class Transactions(
    val arrivalTime: Long ?= null,
    val date : Long ?= null,
    val departTime : Long ?= null,
    val fromStation : Long ?= null,
    val reachTime : Long ?= null,
    val toStation : Long ?= null,
    val transactionID : Long ?= null )


