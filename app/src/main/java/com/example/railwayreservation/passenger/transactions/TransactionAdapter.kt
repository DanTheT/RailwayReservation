package com.example.railwayreservation.passenger.transactions

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

class TransactionAdapter(val myContext: Context, val layoutResId: Int, val transactionList: List<Transaction>)
    : ArrayAdapter<Transaction>(myContext, layoutResId, transactionList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val transID = view.findViewById<TextView>(R.id.tvTransID)
        val reservationDate = view.findViewById<TextView>(R.id.tvReservationDate)
        val trainName = view.findViewById<TextView>(R.id.tvTrainName)
        val coach = view.findViewById<TextView>(R.id.tvCoach)
        val seatCat = view.findViewById<TextView>(R.id.tvSeatCat)
        val seat = view.findViewById<TextView>(R.id.tvSeat)
        val fromStation = view.findViewById<TextView>(R.id.tvFromStation)
        val nextStation = view.findViewById<TextView>(R.id.tvNextStation)
        val arriveTime = view.findViewById<TextView>(R.id.tvArriveTime)
        val reachTime = view.findViewById<TextView>(R.id.tvReachTime)

        val transaction = transactionList[position]

        transID.text = transaction.id
        reservationDate.text = transaction.reservationDate
        trainName.text = transaction.trainName
        coach.text = transaction.coach
        seatCat.text = transaction.seatCat
        seat.text = transaction.seats
        fromStation.text = transaction.fromStation
        nextStation.text = transaction.nextStation
        arriveTime.text = transaction.arriveTime
        reachTime.text = transaction.reachTime

        return view

    }
}

//        val btnCopy = view.findViewById<TextView>(R.id.btnCopy)
//        btnCopy.setOnClickListener(View.OnClickListener() {
//        fun Context.copyToClipboard(text: CharSequence){
//            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clip = ClipData.newPlainText("label",transID.text)
//            clipboard.setPrimaryClip(clip)
//            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
//        }




