package com.example.railwayreservation.admin.trainSeats.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedSeat(
    val coachNum: String
): Parcelable
