package com.example.railwayreservation.admin.trainInfo.data

import androidx.lifecycle.MutableLiveData

class TrainInfoDAO {
    private val trainInfoList = mutableListOf<BriefInfoData>()
    private val trainInfo = MutableLiveData<List<BriefInfoData>>()

    init {
        trainInfo.value = trainInfoList
    }


}