package com.example.railwayreservation.admin.trainSchedule

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
import com.example.railwayreservation.admin.trainSchedule.data.Schedule
import com.example.railwayreservation.databinding.FragmentAddNewScheduleBinding

class AddNewScheduleFragment : Fragment() {

    private var _binding: FragmentAddNewScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var scheduleViewModel: AddScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleViewModel = ViewModelProvider(this)[AddScheduleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewScheduleBinding.inflate(inflater, container, false)

        insertTrainNames()
        insertScheduleArriveTime()
        insertScheduleReachTime()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.addScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_addNewScheduleFragment_to_overallTrainScheduleFragment)
        }

        binding.buttonAddGetStation.setOnClickListener {
            when (binding.textScheduleTrainName.text.toString()) {
                "Angsana" -> {
                    insertScheduleStartStation()
                    insertScheduleNextStation()
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

        binding.addNewScheduleBtn.setOnClickListener {
            val arriveTime = binding.textScheduleArriveTime.text.toString()
            val reachTime = binding.textScheduleReachTime.text.toString()

            try {
                if (reachTime > arriveTime) {
                    insertNewSchedule()
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "$reachTime cannot less / equal the $arriveTime",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    private fun insertTrainNames() {
        val trainName = resources.getStringArray(R.array.train_name_items)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, trainName)
        binding.textScheduleTrainName.setAdapter(listsAdapter)
    }

    private fun insertScheduleStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainStartStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleStartStation.setAdapter(listsAdapter)
    }

    private fun insertScheduleNextStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStation.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationB() {
        val lists = resources.getStringArray(R.array.station_for_balak)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStation.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationC() {
        val lists = resources.getStringArray(R.array.station_for_chino)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStation.setAdapter(listsAdapter)
    }

    private fun insertTrainNextStationOther() {
        val lists = resources.getStringArray(R.array.station_for_other)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textScheduleNextStation.setAdapter(listsAdapter)
    }

    private fun insertScheduleArriveTime() {
        val timeLists = resources.getStringArray(R.array.schedule_time)

        val timeAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, timeLists)
        binding.textScheduleArriveTime.setAdapter(timeAdapter)
    }

    private fun insertScheduleReachTime() {
        val timeLists = resources.getStringArray(R.array.schedule_time)

        val timeAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, timeLists)
        binding.textScheduleReachTime.setAdapter(timeAdapter)
    }

    private fun insertNewSchedule() {
        val trainName = binding.textScheduleTrainName.text.toString()
        val startStation = binding.textScheduleStartStation.text.toString()
        val arriveTime = binding.textScheduleArriveTime.text.toString()
        val nextStation = binding.textScheduleNextStation.text.toString()
        val reachTime = binding.textScheduleReachTime.text.toString()
        val status = "Available"

        val scheduleInfo = Schedule(
            trainName, startStation, arriveTime, nextStation, reachTime, status
        )

        scheduleViewModel.insertNewSchedule(trainName, arriveTime, scheduleInfo)
    }
}