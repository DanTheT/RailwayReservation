package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class TrainInfoAdapter(private val trainList: ArrayList<BriefInfoData>): RecyclerView.Adapter<TrainInfoAdapter.TrainViewHolder>() {

    class TrainViewHolder(trainView: View): RecyclerView.ViewHolder(trainView) {
        val trainNameInfo: TextView = trainView.findViewById(R.id.trainInfoName)
        val trainLineInfo: TextView = trainView.findViewById(R.id.trainInfoLine)
        val trainStartStationInfo: TextView = trainView.findViewById(R.id.trainInfoStartStation)
        val trainEndStationInfo: TextView = trainView.findViewById(R.id.trainInfoLastStation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val trainView = LayoutInflater.from(parent.context).inflate(R.layout.list_info, parent, false)
        return TrainViewHolder(trainView)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val train = trainList[position]
        holder.trainNameInfo.text = train.trainType
        holder.trainLineInfo.text = train.trainLine
        holder.trainStartStationInfo.text = train.startStation
        holder.trainEndStationInfo.text = train.endStation
    }

    override fun getItemCount(): Int {
        return trainList.size
    }
}