package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class CheckTrainInfoAdapter(private val trainInfoList: ArrayList<TrainInfoRecycle>): RecyclerView.Adapter<CheckTrainInfoAdapter.TrainInfoHolder>() {
    class TrainInfoHolder(trainInfoView: View): RecyclerView.ViewHolder(trainInfoView) {
        val checkTrainType: TextView = trainInfoView.findViewById(R.id.checkTypeTxt)
        val checkTrainLine: TextView = trainInfoView.findViewById(R.id.checkLaneTxt)
        val checkTrainStartStation: TextView = trainInfoView.findViewById(R.id.checkStartTxt)
        val checkTrainEndStation: TextView = trainInfoView.findViewById(R.id.checkEndTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainInfoHolder {
        val trainView = LayoutInflater.from(parent.context).inflate(R.layout.list_info, parent, false)
        return TrainInfoHolder(trainView)
    }

    override fun onBindViewHolder(holder: TrainInfoHolder, position: Int) {
        val trainInfo: TrainInfoRecycle = trainInfoList[position]
        holder.checkTrainType.text = trainInfo.trainType
        holder.checkTrainLine.text = trainInfo.trainLine
        holder.checkTrainStartStation.text = trainInfo.startStation
        holder.checkTrainEndStation.text = trainInfo.endStation
    }

    override fun getItemCount(): Int {
        return trainInfoList.size
    }
}