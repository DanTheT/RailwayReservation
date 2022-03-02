package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.FirebaseDatabase

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
        //insertScheduleArriveTime()
        //insertScheduleReachTime()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.addScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_addNewScheduleFragment_to_overallTrainScheduleFragment)
        }
/*
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

 */

        binding.addNewScheduleBtn.setOnClickListener {
            try {
                route1()
                Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                e.message
            }
        }

        binding.addNewScheduleBtn2.setOnClickListener {
            try {
                if (binding.textScheduleTrainName.text.toString() == "Angsana") {
                    route1reverse()
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                val tag = "Add new scheudle"
                Log.d(tag, "${e.message}")
            }
        }
    }

    private fun insertTrainNames() {
        val trainName = resources.getStringArray(R.array.train_name_items)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, trainName)
        binding.textScheduleTrainName.setAdapter(listsAdapter)
    }

/*
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

 */

    private fun route1() {
        val status = "Available"
        val destinations = arrayListOf(
            "Angsana",
            "Avion",
            "Balak",
            "Chino",
            "Dalluman",
            "Edward",
            "Federick",
            "Golum",
            "Helumin",
            "Indigo",
            "Jesper",
        )

        val times = arrayListOf(
            "0800",
            "0830",
            "0900",
            "0930",
            "1000",
            "1030",
            "1100",
            "1130",
            "1200",
            "1230",
            "1300", //11
        )
        for (i in 0 until times.size - 1) {
            val route = Schedule(
                destinations[0],
                destinations[i],
                times[i],
                destinations[i + 1],
                times[i + 1],
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[0], times[i], route)
        }
    }

    private fun route1reverse() {
        val status = "Available"
        val destinations = arrayListOf(
            "Jesper",
            "Indigo",
            "Helumin",
            "Golum",
            "Federick",
            "Edward",
            "Dalluman",
            "Chino",
            "Balak",
            "Avion",
            "Angsana",
        )

        val times = arrayListOf(
            "1300",
            "1330",
            "1400",
            "1430",
            "1500",
            "1530",
            "1600",
            "1630",
            "1700",
            "1730",
            "1800",
        )
        for (i in 0 until (times.size - 1)) {
            val route = Schedule(
                destinations[10],
                destinations[i],
                times[i],
                destinations[i + 1],
                times[i + 1],
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[10], times[i], route)
        }
    }
}