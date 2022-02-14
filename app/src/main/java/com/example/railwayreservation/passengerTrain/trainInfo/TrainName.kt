package com.example.railwayreservation.passengerTrain.trainInfo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainName(
    val trainName: String = ""
): Parcelable
