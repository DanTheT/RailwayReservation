package com.example.railwayreservation.admin.trainSeats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class SeatsAdapter(private val seatsList: ArrayList<SeatsData>): RecyclerView.Adapter<SeatsAdapter.SeatsViewHolder>() {
    class SeatsViewHolder(seatsView: View): RecyclerView.ViewHolder(seatsView) {
        val seatAvailable: TextView = seatsView.findViewById(R.id.textViewAvailable)
        val seatCoachNum: TextView = seatsView.findViewById(R.id.textViewName)
        val seatNumber: TextView = seatsView.findViewById(R.id.textViewSeatNo)
        val seatReserved: TextView = seatsView.findViewById(R.id.textViewSeatReserve)
        val seatFare: TextView = seatsView.findViewById(R.id.textViewSeatFare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatsViewHolder {
        val seatView = LayoutInflater.from(parent.context).inflate(R.layout.list_seats_admin, parent, false)
        return SeatsViewHolder(seatView)
    }

    override fun onBindViewHolder(holder: SeatsViewHolder, position: Int) {
        val seats: SeatsData = seatsList[position]
        holder.seatAvailable.text = seats.available
        holder.seatCoachNum.text = seats.coachNum
        holder.seatNumber.text = seats.seatNo
        holder.seatReserved.text = seats.reserved
        holder.seatFare.text = seats.seatPrice
    }

    override fun getItemCount(): Int {
        return seatsList.size
    }
}