package com.example.railwayreservation.reportIssue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class IssueAdapter(private val issueList: ArrayList<IssuesData>): RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    class IssueViewHolder(issueView: View): RecyclerView.ViewHolder(issueView) {
        val receivedIssue: TextView = issueView.findViewById(R.id.received_issue_txt)
        val receivedCarId: TextView = issueView.findViewById(R.id.received_car_id_txt)
        val receivedIssueId: TextView = issueView.findViewById(R.id.received_issue_id_txt)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IssueViewHolder {
        val issueView = LayoutInflater.from(parent.context).inflate(R.layout.list_issues, parent, false)
        return IssueViewHolder(issueView)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val issue: IssuesData = issueList[position]
        holder.receivedIssueId.text = issue.issueId
        holder.receivedIssue.text = issue.receivedIssue
        holder.receivedCarId.text = issue.carId
    }

    override fun getItemCount(): Int {
        return issueList.size
    }
}