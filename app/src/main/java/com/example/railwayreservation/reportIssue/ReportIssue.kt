package com.example.railwayreservation.reportIssue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityReportIssueBinding
import com.example.railwayreservation.passenger.PassengerHome
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReportIssue : AppCompatActivity() {

    private lateinit var binding: ActivityReportIssueBinding
    private lateinit var issueDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportIssueBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.reportIssueMainTopAppBar.setOnClickListener {
            startActivity(Intent(baseContext, PassengerHome::class.java))
        }

        insertCategory()
        insertTrainName()
        insertCoachNum()

        binding.reportBtn.setOnClickListener {
            val issueCategory = binding.reportIssueSpinner.text.toString()
            val issueDesc = binding.reportIssueDesc.text.toString()
            val issueTrain = binding.reportIssueTrainChoose.text.toString()
            val issueCoach = binding.reportIssueCoachChoose.text.toString()
            val issueProg = "In Progress"

            val getDateTime = LocalDateTime.now()
            val formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            val dateTimeFormatted = getDateTime.format(formatDateTime)

            val getDate = LocalDate.now()
            val formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val dateFormatted = getDate.format(formatDate)

            val getTime = LocalTime.now()
            val formatTime = DateTimeFormatter.ofPattern("HH:mm")
            val timeFormatted = getTime.format(formatTime)

            val issues = IssuesData(issueCategory, issueDesc, dateFormatted, timeFormatted, issueProg, issueTrain, issueCoach)

            issueDatabase = FirebaseDatabase.getInstance().getReference("Issues")
            if (issueCategory.isNotEmpty() && issueDesc.isNotEmpty()) {
                try {
                    issueDatabase.child(issueCategory).child(dateTimeFormatted).setValue(issues).addOnSuccessListener {
                        Toast.makeText(baseContext, "Report success for $issueCategory at $timeFormatted", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception) {
                    Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(baseContext, "Please check category is chosen & provide a bit of desc", Toast.LENGTH_SHORT).show()
            }
        }

        binding.reportIssueMainTopAppBar.setOnClickListener {
            startActivity(Intent(this, PassengerHome::class.java))
        }
    }

    private fun insertCategory() {
        val lists = resources.getStringArray(R.array.report_category_items)

        val listAdapter = ArrayAdapter(baseContext, R.layout.list_for_dropdown, lists)
        binding.reportIssueSpinner.setAdapter(listAdapter)
    }

    private fun insertTrainName() {
        val lists = resources.getStringArray(R.array.train_name_items)

        val listAdapter = ArrayAdapter(baseContext, R.layout.list_for_dropdown, lists)
        binding.reportIssueTrainChoose.setAdapter(listAdapter)
    }

    private fun insertCoachNum() {
        val lists = resources.getStringArray(R.array.five_coaches)

        val listAdapter = ArrayAdapter(baseContext, R.layout.list_for_dropdown, lists)
        binding.reportIssueCoachChoose.setAdapter(listAdapter)
    }
}