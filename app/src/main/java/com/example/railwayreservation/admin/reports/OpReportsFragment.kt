package com.example.railwayreservation.admin.reports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.OpReportsFragmentBinding
import com.google.firebase.database.*

class OpReportsFragment : Fragment() {

    private var _binding: OpReportsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var getData: DatabaseReference
    val taging = "OpReports"

    companion object {
        fun newInstance() = OpReportsFragment()
    }

    private lateinit var viewModel: OpReportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OpReportsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OpReportsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.overallReportOperationTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_opReportsFragment_to_adminMainFragment)
        }

        binding.fetchDataBtn.setOnClickListener {
            try {
                getTotalTrain()
                getTotalTrainStatus()
                getTotalTransactions()
                getTotalCancellations()
            } catch (e: Exception) {
                Log.d(taging, "${e.message}")
            }
        }
    }

    private fun getTotalTrain() {
        var numOfTrain = 0
        getData = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    numOfTrain = snapshot.childrenCount.toInt()
                    binding.displayTextTotalTrain.text = numOfTrain.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun getTotalTrainStatus() {
        var numOfTrain = 0
        getData = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo").child("status")
        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    when(snapshot.value) {
                        "Deactivate" -> {
                            numOfTrain = snapshot.childrenCount.toInt()
                            binding.displayTextTotalTrainStatus.text = numOfTrain.toString()
                        } else -> {
                            binding.displayTextTotalTrainStatus.text = "0"
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun getTotalTransactions() {
        var numOfTransactions = 0
        getData = FirebaseDatabase.getInstance().getReference("Transactions")
        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    numOfTransactions = snapshot.childrenCount.toInt()
                    binding.displayTextTotalTrainTransactions.text = numOfTransactions.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun getTotalCancellations() {
        var numOfCancellations = 0
        getData = FirebaseDatabase.getInstance().getReference("Cancellation")
        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    numOfCancellations = snapshot.childrenCount.toInt()
                    binding.displayTextTotalTrainCancellations.text = numOfCancellations.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}