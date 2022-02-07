package com.example.railwayreservation.reportIssue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityIssueManageBinding
import com.google.firebase.database.*

class IssueManage : AppCompatActivity() {

    private lateinit var binding: ActivityIssueManageBinding
    private lateinit var issueRecycleView: RecyclerView
    private lateinit var issueArrayList: ArrayList<IssuesData>
    private lateinit var issueDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueManageBinding.inflate(layoutInflater)
        val view = binding.root

        issueRecycleView = binding.displayIssuesRecyclerView
        issueRecycleView.layoutManager = LinearLayoutManager(this)

        issueArrayList = arrayListOf<IssuesData>()
        fetchIssueData()

        setContentView(view)
    }

    private fun fetchIssueData() {
        issueDatabase = FirebaseDatabase.getInstance().getReference("Issues")
        issueDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issueSnapshot in snapshot.children) {
                        val issue = issueSnapshot.getValue(IssuesData::class.java)
                        issueArrayList.add(issue!!)
                    }
                    issueRecycleView.adapter = IssueAdapter(issueArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}