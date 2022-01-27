package com.example.railwayreservation.passenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class TransactionAdapter(private var historyList: ArrayList<Transactions>) : RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.purchase_history,
        parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: TransactionAdapter.MyViewHolder, position: Int) {

        val currentItem = historyList[position]

        holder.arrivalTime.text = currentItem.arrivalTime.toString().trim()
        holder.date.text = currentItem.date.toString().trim()
        holder.departTime.text = currentItem.departTime.toString().trim()
        holder.fromStation.text = currentItem.fromStation.toString().trim()
        holder.reachTime.text = currentItem.reachTime.toString().trim()
        holder.toStation.text = currentItem.toStation.toString().trim()
        holder.transactionID.text = currentItem.transactionID.toString().trim()
    }

    override fun getItemCount(): Int {

        return historyList.size

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val arrivalTime : TextView = itemView.findViewById(R.id.schedule_arriveTime)
        val date : TextView = itemView.findViewById(R.id.schedule_Date)
        val departTime : TextView = itemView.findViewById(R.id.schedule_departTime)
        val fromStation : TextView = itemView.findViewById(R.id.schedule_fromStation)
        val reachTime : TextView = itemView.findViewById(R.id.schedule_reachTime)
        val toStation : TextView = itemView.findViewById(R.id.schedule_toStation)
        val transactionID : TextView = itemView.findViewById(R.id.tvTransactionId)
    }
}