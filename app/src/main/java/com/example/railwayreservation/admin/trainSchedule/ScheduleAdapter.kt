package com.example.railwayreservation.admin.trainSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class ScheduleAdapter(private val scheduleList: ArrayList<Schedule>): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(scheduleView: View): RecyclerView.ViewHolder(scheduleView) {
        val fromStation: TextView = scheduleView.findViewById(R.id.admin_scheduleFrom)
        val nextStation: TextView = scheduleView.findViewById(R.id.admin_scheduleNext)
        val arriveTime: TextView = scheduleView.findViewById(R.id.admin_scheduleArrive)
        val reachTime: TextView = scheduleView.findViewById(R.id.admin_scheduleReach)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val scheduleView = LayoutInflater.from(parent.context).inflate(R.layout.list_schedule, parent, false)
        return ScheduleViewHolder(scheduleView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule: Schedule = scheduleList[position]
        holder.fromStation.text = schedule.fromStation
        holder.nextStation.text = schedule.nextStation
        holder.arriveTime.text = schedule.arriveTime
        holder.reachTime.text = schedule.reachTime
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}