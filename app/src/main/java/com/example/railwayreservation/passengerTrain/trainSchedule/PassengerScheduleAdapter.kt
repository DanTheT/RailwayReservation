package com.example.railwayreservation.passengerTrain.trainSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class PassengerScheduleAdapter(private val scheduleList: ArrayList<ScheduleData>, private val itemClick: OnItemClick): RecyclerView.Adapter<PassengerScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(scheduleView: View): RecyclerView.ViewHolder(scheduleView) {
        val receivedName: TextView = scheduleView.findViewById(R.id.schedule_trainName)
        val receivedFromStation: TextView = scheduleView.findViewById(R.id.schedule_fromStation)
        val receivedArriveTime: TextView = scheduleView.findViewById(R.id.schedule_arriveTime)
        val receivedNextStation: TextView = scheduleView.findViewById(R.id.schedule_nextStation)
        val receivedReachTime: TextView = scheduleView.findViewById(R.id.schedule_reachTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val scheduleView = LayoutInflater.from(parent.context).inflate(R.layout.list_schedule_passenger, parent, false)
        return ScheduleViewHolder(scheduleView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule: ScheduleData = scheduleList[position]
        holder.receivedName.text = schedule.trainName
        holder.receivedFromStation.text = schedule.fromStation
        holder.receivedArriveTime.text = schedule.arriveTime
        holder.receivedNextStation.text = schedule.nextStation
        holder.receivedReachTime.text = schedule.reachTime

        holder.itemView.setOnClickListener {
            itemClick.onItemSelectClick(schedule)
        }
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    interface OnItemClick {
        fun onItemSelectClick(data: ScheduleData)
    }
}