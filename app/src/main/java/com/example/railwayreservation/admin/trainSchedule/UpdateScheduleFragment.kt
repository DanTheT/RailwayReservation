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
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainSchedule.data.Schedule
import com.example.railwayreservation.databinding.FragmentUpdateScheduleBinding

class UpdateScheduleFragment : Fragment() {

    var trainName: String? = null
    private var _binding: FragmentUpdateScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var scheduleViewModel: UpdateScheduleViewModel
    private val args by navArgs<UpdateScheduleFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleViewModel = ViewModelProvider(this)[UpdateScheduleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateScheduleBinding.inflate(inflater, container, false)

        binding.updateTrainScheduleText.text = args.receivedName.trainName

        insertScheduleArriveTime()
        insertScheduleReachTime()
        insertScheduleStatus()

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

        binding.updateNewScheduleBtn.setOnClickListener {
            val trainName = binding.updateTrainScheduleText.text.toString()
            val startStation = binding.textScheduleStartStationUpdate.text.toString()
            val arriveTime = binding.textScheduleArriveTimeUpdate.text.toString()
            val nextStation = binding.textScheduleNextStationUpdate.text.toString()
            val reachTime = binding.textScheduleReachTimeUpdate.text.toString()
            val status = binding.textFieldUpdateTrainStatus.text.toString()

            val scheduleUpdate = Schedule(
                trainName, startStation, arriveTime, nextStation, reachTime, status
            )
            try {
                val updateArriveTime = binding.textScheduleArriveTimeUpdate.text.toString()
                val updateReachTime = binding.textScheduleNextStationUpdate.text.toString()

                if (updateReachTime > updateArriveTime) {
                    scheduleViewModel.updateSchedule(trainName, arriveTime, scheduleUpdate)
                    Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(), "$reachTime cannot less / equal the $arriveTime", Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
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

    private fun insertScheduleArriveTime() {
        val timeLists = resources.getStringArray(R.array.schedule_time)

        val timeAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, timeLists)
        binding.textScheduleArriveTimeUpdate.setAdapter(timeAdapter)
    }

    private fun insertScheduleReachTime() {
        val timeLists = resources.getStringArray(R.array.schedule_time)

        val timeAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, timeLists)
        binding.textScheduleReachTimeUpdate.setAdapter(timeAdapter)
    }

    private fun insertScheduleStatus() {
        val statusLists = resources.getStringArray(R.array.status_items)

        val timeAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, statusLists)
        binding.textFieldUpdateTrainStatus.setAdapter(timeAdapter)
    }
}