package com.example.railwayreservation.reportIssue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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

        insertIssueCategory()

        issueRecycleView = binding.displayIssuesRecyclerView
        issueRecycleView.layoutManager = LinearLayoutManager(this)

        issueArrayList = arrayListOf<IssuesData>()

        binding.searchCategory.setOnClickListener {
            val selectCategory = binding.textFieldSearchCategory.text.toString()
            fetchIssueData(selectCategory)
        }

        binding.clearRecyclerCategory.setOnClickListener {
            try {
                if (issueRecycleView.isShown) {
                    issueArrayList.clear()
                    issueRecycleView.adapter?.notifyDataSetChanged()
                }
            }catch (e: Exception) {
                e.message
            }
        }

        setContentView(view)
    }

    private fun insertIssueCategory() {
        val lists = resources.getStringArray(R.array.report_category_items)

        val listAdapter = ArrayAdapter(baseContext, R.layout.list_for_dropdown, lists)
        binding.textFieldSearchCategory.setAdapter(listAdapter)
    }

    private fun fetchIssueData(category: String) {
        issueDatabase = FirebaseDatabase.getInstance().getReference("Issues").child(category)
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