package com.example.railwayreservation.reportIssue.updates

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentGetUpdatesBinding
import com.example.railwayreservation.reportIssue.IssuesData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class GetUpdatesFragment : Fragment() {

    private var _binding: FragmentGetUpdatesBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<GetUpdatesFragmentArgs>()
    private lateinit var issueDb: DatabaseReference
    val tagForUpdates = "Get Updates"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetUpdatesBinding.inflate(inflater, container, false)

        binding.textViewIssueCat.text = args.getDetails.issueCategory
        binding.textViewIssueDesc.text = args.getDetails.issueDescription
        binding.textViewTrainId.text = args.getDetails.trainId
        binding.textViewCoachPick.text = args.getDetails.coachPick
        binding.spinnerForResolve.setText(args.getDetails.issueResolve)
        binding.textViewJustTrainDate.text = args.getDetails.issueDate
        binding.textViewJustTrainTime.text = args.getDetails.issueTime

        insertSpinnerResolve()

        binding.buttonForwardUpdate.setOnClickListener {
            try {
                updateIssueData()
            } catch (e: Exception) {
                Log.d(tagForUpdates, "${e.message}")
            }
        }

        binding.buttonUpdateUpdates.setOnClickListener {
            try {
                forwardMessage()
            }catch (e: Exception) {
                Log.d(tagForUpdates, "${e.message}")
            }
        }
        return binding.root
    }

    private fun forwardMessage() {
        val issueCat = binding.textViewIssueCat.text.toString()
        val getDesc = binding.textViewIssueDesc.text.toString()
        val getResolve = binding.spinnerForResolve.text.toString()
        val getTrainId = binding.textViewTrainId.text.toString()
        val getTrainCoach = binding.textViewCoachPick.text.toString()

        val getDateTime = LocalDateTime.now()
        val formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val dateTimeFormatted = getDateTime.format(formatDateTime)

        val getDate = LocalDate.now()
        val formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val dateFormatted = getDate.format(formatDate)

        val getTime = LocalTime.now()
        val formatTime = DateTimeFormatter.ofPattern("HH:mm")
        val timeFormatted = getTime.format(formatTime)

        val forwardMsg = IssuesData(
            issueCat, getDesc, dateFormatted, timeFormatted, getResolve, getTrainId, getTrainCoach
        )

        issueDb = FirebaseDatabase.getInstance().getReference("Messages").child(dateTimeFormatted)
        issueDb.setValue(forwardMsg)
    }

    private fun updateIssueData() {
        val issueCat = binding.textViewIssueCat.text.toString()
        val getDate = binding.textViewJustTrainDate.text.toString()
        val getTime = binding.textViewJustTrainTime.text.toString()
        val concateDateTime = "$getDate $getTime"
        val getDesc = binding.textViewIssueDesc.text.toString()
        val getTrainId = binding.textViewTrainId.text.toString()
        val getTrainCoach = binding.textViewCoachPick.text.toString()
        val getResolve = binding.spinnerForResolve.text.toString()

        val newIssue = IssuesData(
            issueCat, getDesc, getDate, getTime, getResolve, getTrainId, getTrainCoach
        )

        issueDb = FirebaseDatabase.getInstance().getReference("Issues").child(issueCat).child(concateDateTime)

        issueDb.setValue(newIssue)
    }

    private fun insertSpinnerResolve() {
        val lists = resources.getStringArray(R.array.status_issue_resolve)

        val listAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.spinnerForResolve.setAdapter(listAdapter)
    }


}