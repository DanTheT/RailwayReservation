package com.example.railwayreservation.admin.trainSchedule.addSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.databinding.FragmentAddScheduleBinding
import com.google.firebase.database.DatabaseReference
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainSchedule.TrainSchedule
import com.example.railwayreservation.admin.trainSchedule.ScheduleManageFragment
import com.google.firebase.database.FirebaseDatabase

class AddScheduleFragment : Fragment() {

    private var _binding: FragmentAddScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddScheduleBinding.inflate(inflater, container, false)

        insertArrivalTimeSpinner()
        insertDepartureTimeSpinner()
        insertStartStationSpinner()
        insertEndStationSpinner()

        binding.backBtn.setOnClickListener {
            val backSchedulePage = activity as NavigationFrag
            backSchedulePage.navFrag(ScheduleManageFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFunction()
        checkTimeSelected()
    }

    private fun insertArrivalTimeSpinner(){
        val spinner: Spinner = binding.scheduleStartTimeSpinner
        ArrayAdapter.createFromResource(requireContext(), R.array.schedule_time,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun insertDepartureTimeSpinner(){
        val spinner: Spinner = binding.scheduleDepartTimeSpinner
        ArrayAdapter.createFromResource(requireContext(),R.array.schedule_time,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun insertStartStationSpinner(){
        val spinner: Spinner = binding.selectStationSpinner
        ArrayAdapter.createFromResource(requireContext(),R.array.station_names,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun insertEndStationSpinner(){
        val spinner: Spinner = binding.selectEndStationSpinner
        ArrayAdapter.createFromResource(requireContext(),R.array.station_names,android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun addFunction(){
        binding.scheduleAddBtn.setOnClickListener {
            val trainType = binding.addTrainTypeTxt.text.toString()
            val startStation = binding.selectStationSpinner.selectedItem.toString()
            val endStation = binding.selectEndStationSpinner.selectedItem.toString()
            val arrivalTime = binding.scheduleStartTimeSpinner.selectedItem.toString()
            val departureTime = binding.scheduleDepartTimeSpinner.selectedItem.toString()

            checkNode(trainType, startStation, endStation, arrivalTime, departureTime)
        }
    }

    private fun checkNode(trainType: String, startStation: String, endStation: String, arrivalTime: String, departureTime: String){
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainType).child("Schedule")

        val isExist = true
        val schedule = TrainSchedule(startStation, endStation, arrivalTime, departureTime)

        if(scheduleDatabase.child(startStation).equals(isExist)){
            Toast.makeText(requireContext(), "This start station exist already", Toast.LENGTH_LONG).show()
        }else {
            scheduleDatabase.setValue(schedule).addOnSuccessListener {
                binding.addTrainTypeTxt.text.clear()

                Toast.makeText(context, "Add Successful at $trainType", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Add Failed at $trainType", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkTimeSelected(){
        val startTime = binding.scheduleStartTimeSpinner.selectedItem.toString()
        val endTime = binding.scheduleDepartTimeSpinner.selectedItem.toString()

        if(startTime <= endTime){
            Toast.makeText(requireContext(), "$startTime + and $endTime are clockwise", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Time selection is illogical", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}