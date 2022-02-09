package com.example.railwayreservation.admin.trainSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class ScheduleAdapter(private val scheduleList: ArrayList<Schedule>): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(scheduleView: View): RecyclerView.ViewHolder(scheduleView) {
        val trainName: TextView = scheduleView.findViewById(R.id.admin_trainName)
        val fromStation: TextView = scheduleView.findViewById(R.id.admin_scheduleFrom)
        val nextStation: TextView = scheduleView.findViewById(R.id.admin_scheduleNext)
        val arriveTime: TextView = scheduleView.findViewById(R.id.admin_scheduleArrive)
        val reachTime: TextView = scheduleView.findViewById(R.id.admin_scheduleReach)
        val status: TextView = scheduleView.findViewById(R.id.admin_scheduleStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val scheduleView = LayoutInflater.from(parent.context).inflate(R.layout.list_schedule, parent, false)
        return ScheduleViewHolder(scheduleView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule: Schedule = scheduleList[position]
        holder.trainName.text = schedule.trainName
        holder.fromStation.text = schedule.fromStation
        holder.nextStation.text = schedule.nextStation
        holder.arriveTime.text = schedule.arriveTime
        holder.reachTime.text = schedule.reachTime
        holder.status.text = schedule.status
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}