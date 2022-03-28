package com.example.railwayreservation.passengerTrain.trainInfo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelizedNameDest(
    val trainName: String = "",
    val fromStation: String = "",
    val nextStation: String = "",
    val reachTime: String = "",
    val arrivalTime: String = "",


    ): Parcelable
