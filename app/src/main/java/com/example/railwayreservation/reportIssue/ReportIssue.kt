package com.example.railwayreservation.reportIssue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityAdminLoginBinding
import com.example.railwayreservation.databinding.ActivityReportIssueBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReportIssue : AppCompatActivity() {

    private lateinit var binding: ActivityReportIssueBinding
    private lateinit var issueDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportIssueBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.reportBtn.setOnClickListener {
            val id = binding.issueSpinner.selectedItem.toString()
            val issue = binding.describeText.text.toString()
            val carId = binding.coachIdText.text.toString()
            var initialNumber = 5

            val issues = IssuesData(id, issue, carId)

            issueDatabase = FirebaseDatabase.getInstance().getReference("Issues")

        }
    }
}