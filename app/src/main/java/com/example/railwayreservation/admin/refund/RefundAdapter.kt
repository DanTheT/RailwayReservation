package com.example.railwayreservation.admin.refund

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.example.railwayreservation.R


class RefundAdapter(val myContext: Context, val layoutResId: Int, val refundList: List<Refund>)
    : ArrayAdapter<Refund>(myContext, layoutResId, refundList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val transID = view.findViewById<TextView>(R.id.tvTransID)
        val reservationDate = view.findViewById<TextView>(R.id.tvReservationDate)
        val trainName = view.findViewById<TextView>(R.id.tvTrainName)
        val coach = view.findViewById<TextView>(R.id.tvCoach)
        val seatCat = view.findViewById<TextView>(R.id.tvSeatCat)
        val seatPrice = view.findViewById<TextView>(R.id.tvSeatPrice)
        val fromStation = view.findViewById<TextView>(R.id.tvFromStation)
        val nextStation = view.findViewById<TextView>(R.id.tvNextStation)
        val arriveTime = view.findViewById<TextView>(R.id.tvArriveTime)
        val reachTime = view.findViewById<TextView>(R.id.tvReachTime)
        val btnApprove = view.findViewById<Button>(R.id.btnApprove)
        val btnReject = view.findViewById<Button>(R.id.btnReject)


        val refund = refundList[position]

        transID.text = refund.id
        reservationDate.text = refund.reservationDate
        trainName.text = refund.trainName
        coach.text = refund.coach
        seatCat.text = refund.seatCat
        seatPrice.text = refund.seatPrice
        fromStation.text = refund.fromStation
        nextStation.text = refund.nextStation
        arriveTime.text = refund.arriveTime
        reachTime.text = refund.reachTime

        btnApprove.setOnClickListener{
            btnApprove.text = "Approved"
            btnReject.isEnabled = false

        }

        btnReject.setOnClickListener{
            btnReject.text = "Rejected"
            btnApprove.isEnabled = false
        }

        return view

    }

}





