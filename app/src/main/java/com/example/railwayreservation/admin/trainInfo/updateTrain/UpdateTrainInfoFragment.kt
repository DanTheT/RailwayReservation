package com.example.railwayreservation.admin.trainInfo.updateTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo
import com.example.railwayreservation.databinding.FragmentUpdateTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class UpdateTrainInfoFragment : Fragment() {

    private var _binding: FragmentUpdateTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var trainName: String? = null
    private lateinit var updateViewModel: UpdateTrainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainName = requireArguments().getString("trainName")
        updateViewModel = ViewModelProvider(this)[UpdateTrainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTrainInfoBinding.inflate(inflater, container, false)

        insertTrainStartStation()
        insertTrainEndStation()
        insertTrainCoaches()
        insertTrainStatus()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainInfoMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateTrainInfoFragment_to_overallTrainInfoFragment)
        }

        val trainName = "$trainName"
        binding.textFieldEditTrainName.setText(trainName)

        binding.updateNewTrainInfoBtn.setOnClickListener {
            val trainLine = binding.textFieldEditTrainLine.text.toString().trim()
            val startStation = binding.textFieldEditStartStation.text.toString()
            val endStation = binding.textFieldEditEndStation.text.toString()
            val car = binding.textFieldEditNumberCoach.text.toString()
            val trainNum = binding.textFieldEditTrainNumber.text.toString()
            val status = binding.textFieldEditTrainStatus.text.toString()

            val trainInfo = TrainInfo (
                trainName, trainLine, car, trainNum ,endStation, startStation, status
                    )

            updateViewModel.updateTrain(trainName, trainInfo)
        }
    }

    private fun insertTrainStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditEndStation.setAdapter(listsAdapter)
    }

    private fun insertTrainCoaches() {
        val lists = resources.getStringArray(R.array.numOfTrainCoaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditNumberCoach.setAdapter(listsAdapter)
    }

    private fun insertTrainStatus() {
        val lists = resources.getStringArray(R.array.status_items)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditTrainStatus.setAdapter(listsAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}