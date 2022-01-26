package com.example.railwayreservation.admin.trainSchedule.deleteSchedule

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
import com.example.railwayreservation.databinding.FragmentDeleteScheduleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteScheduleFragment : Fragment() {

    private var _binding: FragmentDeleteScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var deleteDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteScheduleBinding.inflate(inflater, container, false)

        insertStartStationValue()
        insertTrainTypeValue()

        binding.deleteScheduleBtn.setOnClickListener {
            val selectedStartStation = binding.deleteStationScheduleSpinner.selectedItem.toString()
            val selectedTrainType = binding.deleteTrainScheduleSpinner.selectedItem.toString()

            deleteSelectedData(selectedStartStation, selectedTrainType)
        }

        binding.backToDeletePage.setOnClickListener {
            val backToDeletePage = activity as NavigationFrag
            backToDeletePage.navFrag(ScheduleManageFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun deleteSelectedData(selectedStartStation: String, selectedTrainType: String){
        deleteDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule").child(selectedStartStation)
        deleteDatabase.child(selectedTrainType).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Selected value delete successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Selected value delete failed", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertStartStationValue() {
        val spinner: Spinner = binding.deleteStationScheduleSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.station_names,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun insertTrainTypeValue() {
        val spinner: Spinner = binding.deleteTrainScheduleSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.train_type_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}