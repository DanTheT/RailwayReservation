package com.example.railwayreservation.admin.trainInfo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelizeInfo(
    val trainName: String = "",
    val status: String = ""
): Parcelable
