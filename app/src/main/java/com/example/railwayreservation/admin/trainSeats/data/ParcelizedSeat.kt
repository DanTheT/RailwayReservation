package com.example.railwayreservation.admin.trainSeats.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedSeat(
    val coachNum: String,
    val trainName: String,
    val seatNo: String,
): Parcelable
