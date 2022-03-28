package com.example.railwayreservation.passenger.cancellation

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

class CancellationAdapter(val myContext: Context, val layoutResId: Int, val cancellationList: List<Cancellation>)
    : ArrayAdapter<Cancellation>(myContext, layoutResId, cancellationList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val transID = view.findViewById<TextView>(R.id.tvTransID)
        val reservationDate = view.findViewById<TextView>(R.id.tvReservationDate)
        val seatCategory = view.findViewById<TextView>(R.id.tvSeatCategory)
        val totalAmount = view.findViewById<TextView>(R.id.tvTotalAmount)



//        val trainName = view.findViewById<TextView>(R.id.tvTrainName)
//        val coach = view.findViewById<TextView>(R.id.tvCoach)
//        val seatCat = view.findViewById<TextView>(R.id.tvSeatCat)
//        val seat = view.findViewById<TextView>(R.id.tvSeat)
//        val seatPrice = view.findViewById<TextView>(R.id.tvSeatPrice)
//        val fromStation = view.findViewById<TextView>(R.id.tvFromStation)
//        val nextStation = view.findViewById<TextView>(R.id.tvNextStation)
//        val arriveTime = view.findViewById<TextView>(R.id.tvArriveTime)
//        val reachTime = view.findViewById<TextView>(R.id.tvReachTime)
        val btnRequest = view.findViewById<Button>(R.id.btnRequest)
       val statusTV = view.findViewById<TextView>(R.id.statusTV)

        val cancellation = cancellationList[position]

        transID.text = cancellation.id
        reservationDate.text = cancellation.reservationDate
        seatCategory.text = cancellation.seatCategory
        totalAmount.text = cancellation.totalAmount

//        trainName.text = cancellation.trainName
//        coach.text = cancellation.coach
//        seatCat.text = cancellation.seatCat
//        seat.text = cancellation.seats
//        seatPrice.text = cancellation.seatPrice
//        fromStation.text = cancellation.fromStation
//        nextStation.text = cancellation.nextStation
//        arriveTime.text = cancellation.arriveTime
//        reachTime.text = cancellation.reachTime

        btnRequest.setOnClickListener{

            btnRequest.isEnabled = false
            statusTV.text = "Pending"
        }

        return view

    }
}





