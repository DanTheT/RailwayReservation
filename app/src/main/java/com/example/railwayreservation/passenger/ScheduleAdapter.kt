package com.example.railwayreservation.passenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class ScheduleAdapter(private val scheduleList : ArrayList<Schedule>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.schedulecard_layout,
        parent, false)
        return ScheduleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {

        return scheduleList.size
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val trainName: TextView = itemView.findViewById(R.id.trainNameTV)
        val fromStation: TextView = itemView.findViewById(R.id.fromStationTV)
        val arriveTime:  TextView = itemView.findViewById(R.id.arriveTimeTV)
        val nextStation: TextView = itemView.findViewById(R.id. nextStationTV)
        val reachTime: TextView = itemView.findViewById(R.id.reachTimeTV)
        val status: TextView = itemView.findViewById(R.id.statusTV)
    }

}