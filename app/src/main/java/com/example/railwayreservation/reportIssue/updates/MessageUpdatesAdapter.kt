package com.example.railwayreservation.reportIssue.updates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.reportIssue.IssuesData

class MessageUpdatesAdapter(private val messageList: ArrayList<IssuesData>): RecyclerView.Adapter<MessageUpdatesAdapter.MessageViewHolder>() {
    class MessageViewHolder(messageView: View): RecyclerView.ViewHolder(messageView) {
        val receivedIssueDate: TextView = messageView.findViewById(R.id.issueDateTxtMessage)
        val receivedIssueTime: TextView = messageView.findViewById(R.id.issueTimeTxtMessage)
        val receivedIssueCategory: TextView = messageView.findViewById(R.id.issueCategoryMessage)
        val receivedIssueDesc: TextView = messageView.findViewById(R.id.received_issue_txtMessage)
        val receivedIssueSolve: TextView = messageView.findViewById(R.id.issueResolvedMessage)
        val receivedIssueTrainName: TextView = messageView.findViewById(R.id.issueTrainSelectMessage)
        val receivedIssueTrainCoach: TextView = messageView.findViewById(R.id.issueCoachSelectMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val messageView = LayoutInflater.from(parent.context).inflate(R.layout.list_issue_message, parent, false)
        return MessageViewHolder(messageView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message: IssuesData = messageList[position]
        holder.receivedIssueDate.text = message.issueDate
        holder.receivedIssueTime.text = message.issueTime
        holder.receivedIssueCategory.text = message.issueCategory
        holder.receivedIssueDesc.text = message.issueDescription
        holder.receivedIssueSolve.text = message.issueResolve
        holder.receivedIssueTrainName.text = message.trainId
        holder.receivedIssueTrainCoach.text = message.coachPick
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}