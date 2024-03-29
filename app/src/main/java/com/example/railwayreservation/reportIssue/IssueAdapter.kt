package com.example.railwayreservation.reportIssue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R

class IssueAdapter(private val issueList: ArrayList<IssuesData>, private val itemClick: OnItemClick): RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    class IssueViewHolder(issueView: View): RecyclerView.ViewHolder(issueView) {
        val receivedIssueDate: TextView = issueView.findViewById(R.id.issueDateTxt)
        val receivedIssueTime: TextView = issueView.findViewById(R.id.issueTimeTxt)
        val receivedIssueCategory: TextView = issueView.findViewById(R.id.issueCategory)
        val receivedIssueDesc: TextView = issueView.findViewById(R.id.received_issue_txt)
        val receivedIssueSolve: TextView = issueView.findViewById(R.id.issueResolved)
        val receivedIssueTrainName: TextView = issueView.findViewById(R.id.issueTrainSelect)
        val receivedIssueTrainCoach: TextView = issueView.findViewById(R.id.issueCoachSelect)
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
        holder.receivedIssueDate.text = issue.issueDate
        holder.receivedIssueTime.text = issue.issueTime
        holder.receivedIssueCategory.text = issue.issueCategory
        holder.receivedIssueDesc.text = issue.issueDescription
        holder.receivedIssueSolve.text = issue.issueResolve
        holder.receivedIssueTrainName.text = issue.trainId
        holder.receivedIssueTrainCoach.text = issue.coachPick

        holder.itemView.setOnClickListener {
            itemClick.onItemClick(issue)
        }
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

    interface OnItemClick {
        fun onItemClick(data: IssuesData)
    }
}