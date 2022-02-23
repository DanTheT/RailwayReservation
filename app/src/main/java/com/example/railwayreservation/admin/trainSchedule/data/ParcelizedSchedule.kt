package com.example.railwayreservation.admin.trainSchedule.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizedSchedule(
    val trainName: String = ""
): Parcelable
