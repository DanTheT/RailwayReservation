package com.example.railwayreservation.admin.trainSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class TrainScheduleAdapter(private val scheduleList: ArrayList<TrainSchedule>): RecyclerView.Adapter<TrainScheduleAdapter.TrainScheduleViewHolder>() {
    class TrainScheduleViewHolder(scheduleView: View): RecyclerView.ViewHolder(scheduleView) {
        val fromStation: TextView = scheduleView.findViewById(R.id.admin_scheduleFrom)
        val toStation: TextView = scheduleView.findViewById(R.id.admin_scheduleTo)
        val arriveTime: TextView = scheduleView.findViewById(R.id.admin_scheduleArrive)
        val departTime: TextView = scheduleView.findViewById(R.id.admin_scheduleDepart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainScheduleViewHolder {
        val scheduleView = LayoutInflater.from(parent.context).inflate(R.layout.list_schedule, parent, false)
        return TrainScheduleViewHolder(scheduleView)
    }

    override fun onBindViewHolder(holder: TrainScheduleViewHolder, position: Int) {
        val schedule: TrainSchedule = scheduleList[position]
        holder.fromStation.text = schedule.fromStation
        holder.toStation.text = schedule.nextStation
        holder.arriveTime.text = schedule.arrivalTime
        holder.departTime.text = schedule.departureTime
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}