package com.example.railwayreservation.passengerTrain.trainInfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainInfoData(
    val trainName: String = "",
    val trainLine: String = "",
    val startStation: String = "",
    val endStation: String = "",
    val car: String = "",
    val trainNum: String = "",
    val status: String = ""
): Parcelable
