package com.example.railwayreservation.admin.trainSchedule.updateSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainSchedule.ScheduleManageFragment
import com.example.railwayreservation.databinding.FragmentUpdateScheduleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UpdateScheduleFragment : Fragment() {
    private var _binding: FragmentUpdateScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateScheduleBinding.inflate(inflater, container, false)

        insertStartDest()

        binding.updateScheduleBtn.setOnClickListener {
            val startStation = binding.updateStartSpinner.selectedItem.toString()
            val arrivalTime = binding.updateArrivalTime.text.toString()
            val departureTime = binding.updateDepartureTime.text.toString()
            val trainType = binding.updateTrainTypeSchedule.text.toString()
            val endStation = binding.updateEndStation.text.toString()

            scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule")

            val schedule = mapOf<String,String>(
                "arrivalTime" to arrivalTime,
                "departureTime" to departureTime,
                "endStation" to endStation
            )

            scheduleDatabase.child(startStation).child(trainType).updateChildren(schedule).addOnSuccessListener {
                binding.updateArrivalTime.text.clear()
                binding.updateDepartureTime.text.clear()
                binding.updateTrainTypeSchedule.text.clear()
                binding.updateEndStation.text.clear()
                Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backScheduleMain.setOnClickListener {
            val backSchedulePage = activity as NavigationFrag
            backSchedulePage.navFrag(ScheduleManageFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun insertStartDest(){
        val spinner: Spinner = binding.updateStartSpinner
        ArrayAdapter.createFromResource(requireContext(),R.array.station_names,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}