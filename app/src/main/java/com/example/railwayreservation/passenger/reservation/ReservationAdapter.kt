package com.example.railwayreservation.passenger.reservation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.passenger.PassengerTicket
import java.util.*

class ReservationAdapter(val myContext: Context, val layoutResId: Int, val reservationList: List<Reservation>)
    : ArrayAdapter<Reservation>(myContext, layoutResId, reservationList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val transID = view.findViewById<TextView>(R.id.tvTransID)
        val reservationDate = view.findViewById<TextView>(R.id.tvReservationDate)
        val trainName = view.findViewById<TextView>(R.id.tvTrainName)
        val coach = view.findViewById<TextView>(R.id.tvCoach)
        val origin = view.findViewById<TextView>(R.id.tvOrigin)
        val destination = view.findViewById<TextView>(R.id.tvDestination)
        val arriveTime = view.findViewById<TextView>(R.id.tvArriveTime)
        val reachTime = view.findViewById<TextView>(R.id.tvReachTime)
        val seatCategory = view.findViewById<TextView>(R.id.tvSeatCategory)
        val totalAmount = view.findViewById<TextView>(R.id.tvTotalAmount)

        val reservation = reservationList[position]

        transID.text = reservation.id
        reservationDate.text = reservation.reservationDate
        trainName.text = reservation.trainName
        coach.text = reservation.coach
        origin.text = reservation.origin
        destination.text = reservation.destination
        arriveTime.text = reservation.arriveTime
        reachTime.text = reservation.reachTime
        seatCategory.text = reservation.seatCategory
        totalAmount.text = reservation.totalAmount

        return view

    }
}





