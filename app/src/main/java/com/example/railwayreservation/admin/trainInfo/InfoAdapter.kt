package com.example.railwayreservation.admin.trainInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.checkTrain.TrainInfoRecycle

class InfoAdapter(private val infoList: ArrayList<TrainInfoRecycle>): RecyclerView.Adapter<InfoAdapter.IssueViewHolder>() {
    class IssueViewHolder(infoView: View): RecyclerView.ViewHolder(infoView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoAdapter.IssueViewHolder {
        val infoView = LayoutInflater.from(parent.context).inflate(R.layout.list_info, parent, false)
        return InfoAdapter.IssueViewHolder(infoView)
    }

    override fun onBindViewHolder(holder: InfoAdapter.IssueViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return infoList.size
    }

}