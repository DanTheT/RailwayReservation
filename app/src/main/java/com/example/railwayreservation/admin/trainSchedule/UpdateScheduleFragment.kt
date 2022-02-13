package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentUpdateScheduleBinding

class UpdateScheduleFragment : Fragment() {

    var trainName: String? = null
    private var _binding: FragmentUpdateScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainName = requireArguments().getString("trainName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateScheduleBinding.inflate(inflater, container, false)

        val trainName = "$trainName"
        binding.updateTrainScheduleText.text = trainName

        binding.buttonGetUpdateSchedule.setOnClickListener {
            when (binding.updateTrainScheduleText.text.toString()) {
                "Angsana" -> {
                    insertTrainStartStation()
                    insertTrainNextStation()
                }
                "Balak" -> {
                    insertTrainStartStationB()
                    insertTrainNextStationB()
                }
                "Chino" -> {
                    insertTrainStartStationC()
                    insertTrainNextStationC()
                }
                else -> {
                    insertTrainStartStationOther()
                    insertTrainNextStationOther()
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateScheduleFragment_to_overallTrainScheduleFragment)
        }
    }

    private fun insertTrainStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStationUpdate.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStationUpdate.setAdapter(listsAdapter)
    }
}