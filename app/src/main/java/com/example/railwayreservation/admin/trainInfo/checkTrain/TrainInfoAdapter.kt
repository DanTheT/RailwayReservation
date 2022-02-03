package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.BriefInfoData

class TrainInfoAdapter(private val trainList: ArrayList<BriefInfoData>): RecyclerView.Adapter<TrainInfoAdapter.TrainViewHolder>() {

    class TrainViewHolder(trainView: View): RecyclerView.ViewHolder(trainView) {
        val trainNameInfo: TextView = trainView.findViewById(R.id.trainInfoName)
        val trainLineInfo: TextView = trainView.findViewById(R.id.trainInfoLine)
        val trainStartStationInfo: TextView = trainView.findViewById(R.id.trainInfoStartStation)
        val trainEndStationInfo: TextView = trainView.findViewById(R.id.trainInfoLastStation)
        val trainNumCoach: TextView = trainView.findViewById(R.id.trainInfoCoachNum)
        val trainNumber: TextView = trainView.findViewById(R.id.trainInfoTrainNum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val trainView = LayoutInflater.from(parent.context).inflate(R.layout.list_info, parent, false)
        return TrainViewHolder(trainView)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val train: BriefInfoData = trainList[position]
        holder.trainNameInfo.text = train.trainName
        holder.trainLineInfo.text = train.trainLine
        holder.trainStartStationInfo.text = train.startStation
        holder.trainEndStationInfo.text = train.endStation
        holder.trainNumCoach.text = train.car
        holder.trainNumber.text = train.trainNum
    }

    override fun getItemCount(): Int {
        return trainList.size
    }
}