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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.addScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_addNewScheduleFragment_to_overallTrainScheduleFragment)
        }

        binding.addNewScheduleBtn.setOnClickListener {
            try {
                when (binding.textScheduleTrainName.text.toString()) {
                    "Angsana" -> {
                        route1()
                        route1reverse()
                        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "Balak" -> {
                        route2()
                        route2reverse()
                        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "Chino" -> {
                        route3()
                        route3reserve()
                        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        routeOther()
                        routeOtherReserve()
                        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT)
                            .show()
                    }
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

    private fun route1() {
        val status = "Available"
        val route1Speed = 11
        val route1Dist = arrayListOf(4, 5, 5, 4, 3, 5, 4, 5, 5, 4, 3)
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
            500,
            600,
            700,
            800,
            900,
            1000,
            1100,
            1200,
            1300,
            1400,
            1500,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route1Dist[i] * route1Speed
            val route = Schedule(
                destinations[0],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[0], times[i].toString(), route)
        }
    }

    private fun route2() {
        val status = "Available"
        val route2Speed = 11
        val route2Dist = arrayListOf(3, 4, 5, 5, 4, 5, 3, 4, 5, 4, 4)
        val destinations = arrayListOf(
            "Balak",
            "Asper",
            "Cander",
            "Dello",
            "Enpear",
            "Fenfir",
            "Godux",
            "Golumer",
            "Heluminy",
            "Indigoer",
            "Jespert",
        )

        val times = arrayListOf(
            500,
            600,
            700,
            800,
            900,
            1000,
            1100,
            1200,
            1300,
            1400,
            1500,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route2Dist[i] * route2Speed
            val route = Schedule(
                destinations[0],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[0], times[i].toString(), route)
        }
    }

    private fun route3() {
        val status = "Available"
        val route3Speed = 11
        val route3Dist = arrayListOf(3, 3, 4, 5, 4, 4, 3, 4, 5, 5, 4)
        val destinations = arrayListOf(
            "Chino",
            "Dexter",
            "Elluc",
            "Feig",
            "Gallo",
            "Helium",
            "Iglor",
            "Sonja",
            "Nadine",
            "Manna",
            "Tusker",
        )

        val times = arrayListOf(
            500,
            600,
            700,
            800,
            900,
            1000,
            1100,
            1200,
            1300,
            1400,
            1500,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route3Dist[i] * route3Speed
            val route = Schedule(
                destinations[0],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i + 1] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[0], times[i].toString(), route)
        }
    }

    private fun route3reserve() {
        val status = "Available"
        val route3Speed = 11
        val route3Dist = arrayListOf(4, 5, 5, 4, 3, 4, 4, 5, 4, 3, 3)
        val destinations = arrayListOf(
            "Tusker",
            "Manna",
            "Nadine",
            "Sonja",
            "Iglor",
            "Helium",
            "Gallo",
            "Feig",
            "Elluc",
            "Dexter",
            "Chino",
        )

        val times = arrayListOf(
            1500,
            1600,
            1700,
            1800,
            1900,
            2000,
            2100,
            2200,
            2300,
            2310,
            100,
        )

        for (i in 0 until times.size - 1) {
            val findTime = route3Dist[i] * route3Speed
            val route = Schedule(
                destinations[10],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i + 1] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[10], times[i].toString(), route)
        }
    }

    private fun routeOther() {
        val status = "Available"
        val route4Speed = 11
        val route4Dist = arrayListOf(3, 5, 5, 5, 4, 3, 3, 4, 3, 5, 4)
        val getName = binding.textScheduleTrainName.text.toString()
        val destinations = arrayListOf(
            "Axxe",
            "Aitom",
            "Bander",
            "Cappo",
            "Dendum",
            "Exxete",
            "Frank",
            "Golam",
            "Heailer",
            "Indiner",
            "Jons",
        )

        val times = arrayListOf(
            500,
            600,
            700,
            800,
            900,
            1000,
            1100,
            1200,
            1300,
            1400,
            1500,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route4Dist[i] * route4Speed
            val route = Schedule(
                getName,
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i + 1] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(getName, times[i].toString(), route)
        }
    }

    private fun routeOtherReserve() {
        val status = "Available"
        val route4Speed = 11
        val route4Dist = arrayListOf(4, 5, 3, 4, 3, 3, 4, 5, 5, 5, 3)
        val getName = binding.textScheduleTrainName.text.toString()
        val destinations = arrayListOf(
            "Jons",
            "Indiner",
            "Heailer",
            "Golam",
            "Frank",
            "Exxete",
            "Dendum",
            "Cappo",
            "Bander",
            "Aitom",
            "Axxe",
        )

        val times = arrayListOf(
            1500,
            1600,
            1700,
            1800,
            1900,
            2000,
            2100,
            2200,
            2300,
            2310,
            100,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route4Dist[i] * route4Speed
            val route = Schedule(
                getName,
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i + 1] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(getName, times[i].toString(), route)
        }
    }

    private fun route1reverse() {
        val status = "Available"
        val route1Speed = 11
        val route1DistR = arrayListOf(3, 4, 5, 5, 4, 5, 3, 4, 5, 5, 4)
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
            1500,
            1600,
            1700,
            1800,
            1900,
            2000,
            2100,
            2200,
            2300,
            2310,
            100,
        )
        for (i in 0 until (times.size - 1)) {
            val findTime = route1DistR[i] * route1Speed
            val route = Schedule(
                destinations[10],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[10], times[i].toString(), route)
        }
    }

    private fun route2reverse() {
        val status = "Available"
        val route2Speed = 11
        val route2Dist = arrayListOf(4, 4, 5, 4, 3, 5, 4, 5, 5, 4)
        val destinations = arrayListOf(
            "Jespert",
            "Indigoer",
            "Heluminy",
            "Golumer",
            "Godux",
            "Fenfir",
            "Enpear",
            "Dello",
            "Cander",
            "Asper",
            "Balak",
        )

        val times = arrayListOf(
            1500,
            1600,
            1700,
            1800,
            1900,
            2000,
            2100,
            2200,
            2300,
            2310,
            100,
        )
        for (i in 0 until times.size - 1) {
            val findTime = route2Dist[i] * route2Speed
            val route = Schedule(
                destinations[10],
                destinations[i],
                times[i].toString(),
                destinations[i + 1],
                (times[i + 1] + findTime).toString(),
                status
            )
            scheduleViewModel.insertNewSchedule(destinations[10], times[i].toString(), route)
        }
    }
}