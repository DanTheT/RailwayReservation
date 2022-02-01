package com.example.railwayreservation.admin.trainInfo.addTrain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.checkTrain.TrainInfo
import com.example.railwayreservation.databinding.FragmentAddNewInfoBinding
import com.example.railwayreservation.databinding.FragmentOverallTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewInfoFragment : Fragment() {

    val TAG = "Add new Info Frag"

    private var _binding: FragmentAddNewInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var trainDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewInfoBinding.inflate(inflater, container, false)

        insertTrainStartStation()
        insertTrainEndStation()
        insertTrainCoaches()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.addTrainInfoMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_addNewInfoFragment_to_overallTrainInfoFragment)
        }

        binding.addNewTrainInfoBtn.setOnClickListener {
            addInfo()
        }
    }

    private fun insertTrainStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEndStation.setAdapter(listsAdapter)
    }

    private fun insertTrainCoaches() {
        val lists = resources.getStringArray(R.array.numOfTrainCoaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldNumberCoach.setAdapter(listsAdapter)
    }

    private fun addInfo() {
        val trainName = binding.textFieldTrainName.text.toString()
        val trainLine = binding.textFieldTrainLine.text.toString()
        val trainStart = binding.textFieldStartStation.text.toString()
        val trainEnd = binding.textFieldEndStation.text.toString()
        val trainCoach = binding.textFieldNumberCoach.text.toString()
        val trainNumber = binding.textFieldTrainNumber.text.toString()

        trainDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
        val trainInfo = TrainInfo(
            trainName, trainLine, trainCoach, trainNumber, trainEnd, trainStart
        )

        trainDatabase.child(trainName).setValue(trainInfo).addOnSuccessListener {
            binding.textFieldTrainName.text?.clear()
            binding.textFieldTrainLine.text?.clear()
            binding.textFieldTrainNumber.text?.clear()

            Toast.makeText(context, "Add Successful, New Train $trainName", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}