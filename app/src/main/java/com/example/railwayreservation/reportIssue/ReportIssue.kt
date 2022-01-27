package com.example.railwayreservation.reportIssue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            var initials = "Issue "

            val issues = IssuesData(id, issue, carId)

            issueDatabase = FirebaseDatabase.getInstance().getReference("Issues")

            for(i in 1..50){
                var newInitials = initials + i
                issueDatabase.child(newInitials).setValue(issues).addOnSuccessListener {
                    Toast.makeText(this, "Successful added issue", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed added issue", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}