package com.example.railwayreservation.admin.trainInfo.updateTrain

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
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo
import com.example.railwayreservation.databinding.FragmentUpdateTrainInfoBinding
import kotlin.Exception

class UpdateTrainInfoFragment : Fragment() {

    private var _binding: FragmentUpdateTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val args by navArgs<UpdateTrainInfoFragmentArgs>()
    private lateinit var updateViewModel: UpdateTrainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateViewModel = ViewModelProvider(this)[UpdateTrainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateTrainInfoBinding.inflate(inflater, container, false)

        binding.textFieldEditTrainName.text = args.nameNumber.trainName

        binding.textFieldEditTrainStatus.setText(args.nameNumber.status)

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

        binding.buttonGetStationUpdate.setOnClickListener {
            when (binding.textFieldEditTrainName.text.toString()) {
                "Angsana" -> {
                    insertTrainStartStation()
                    insertTrainEndStation()
                }
                "Balak" -> {
                    insertTrainStartStationB()
                    insertTrainEndStationB()
                }
                "Chino" -> {
                    insertTrainStartStationC()
                    insertTrainEndStationC()
                }else -> {
                insertTrainStartStationOther()
                insertTrainEndStationOther()
            }
            }
        }

        binding.updateNewTrainInfoBtn.setOnClickListener {
            val trainName = binding.textFieldEditTrainName.text.toString()
            val startStation = binding.textFieldEditStartStation.text.toString()
            val endStation = binding.textFieldEditEndStation.text.toString()
            val car = binding.textFieldEditNumberCoach.text.toString()
            val trainNum = binding.textFieldEditTrainNumber.text.toString()
            val status = binding.textFieldEditTrainStatus.text.toString()

            val trainInfo = TrainInfo (
                trainName, trainName, car, trainNum ,endStation, startStation, status
                    )
            try {
                updateViewModel.updateTrain(trainName, trainInfo)
                Toast.makeText(requireContext(), "Successfully update $trainName", Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertTrainStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditEndStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditEndStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldEditEndStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

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