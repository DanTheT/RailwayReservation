package com.example.railwayreservation.admin.trainInfo.addTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo
import com.example.railwayreservation.databinding.FragmentAddNewInfoBinding
import java.lang.Exception

class AddNewInfoFragment : Fragment() {

    private var _binding: FragmentAddNewInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var addViewModel: AddNewInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addViewModel = ViewModelProvider(this)[AddNewInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewInfoBinding.inflate(inflater, container, false)

        insertTrainStartStation()
        insertTrainEndStation()
        insertTrainCoaches()

        nameFocus()
        numberFocus()

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

    private fun nameFocus() {
        binding.textFieldTrainName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.trainNameLayout.helperText = null
            }
        }
    }

    private fun numberFocus() {
        binding.textFieldTrainNumber.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.trainNumberLayout.helperText = null
            }
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
        val trainStatus = "Active"

        val trainInfo = TrainInfo(
            trainName, trainLine, trainCoach, trainNumber, trainEnd, trainStart, trainStatus
        )

        val successMsg = "Successfully added new train $trainName"
        val errorMsg = "Failed to set data $trainName"

        try {
            addViewModel.setDatabaseReference(trainName, trainInfo)

            Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}