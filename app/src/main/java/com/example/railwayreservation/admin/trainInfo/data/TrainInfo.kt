package com.example.railwayreservation.admin.trainInfo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainInfo(
    val trainName: String = "",
    var trainLine: String = "",
    var car: String = "",
    var trainNum: String = "",
    var endStation: String = "",
    var startStation: String = "",
    var status: String = "",
): Parcelable
