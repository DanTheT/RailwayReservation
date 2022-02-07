package com.example.railwayreservation.admin.trainSeats.addSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainSeats.SeatsData
import com.example.railwayreservation.admin.trainSeats.SeatsManageFragment
import com.example.railwayreservation.databinding.FragmentAddSeatsBinding
import com.google.firebase.database.*

class AddSeatsFragment : Fragment() {

    private var _binding: FragmentAddSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var seatsDatabase: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var addViewModel: AddSeatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addViewModel = ViewModelProvider(this)[AddSeatsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSeatsBinding.inflate(inflater, container, false)

        insertSeatsCategory()
        //insertSeatsRange()
        //insertSeatsRange2()
        //insertSeatsRange3()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.addTrainSeatsMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_addSeatsFragment_to_overallTrainSeatsFragment)
        }

        binding.buttonCheckCategory.setOnClickListener {
            val trainName = binding.editTextSeatTrainName.text.toString()

            seatsDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
            seatsDatabase.child(trainName).child("car").get().addOnSuccessListener {

                when(it.value.toString()) {
                    "4 Coaches" -> {
                        insertSeatsCoachesFour()
                    }
                    "5 Coaches" -> {
                        insertSeatsCoachesFive()
                    }
                }
            }

            when(binding.editTextSeatCategory.text.toString()) {
                "VIP seats" -> {
                    insertSeatsRange()
                    binding.editTextSeatSelectionStandardWindow.text = null
                    binding.editTextSeatSelectionStandardAlley.text = null
                }
                "Standard seats - At Window" -> {
                    insertSeatsRange2()
                    binding.editTextSeatSelection.text = null
                    binding.editTextSeatSelectionStandardAlley.text = null
                }
                else -> {
                    insertSeatsRange3()
                    binding.editTextSeatSelection.text = null
                    binding.editTextSeatSelectionStandardWindow.text = null
                }
            }
        }

        binding.buttonAddSeats.setOnClickListener {
            try {
                insertSeatData()
            }catch (e: Exception){
                e.message
            }
        }
    }

    private fun insertSeatsCategory() {
        val lists = resources.getStringArray(R.array.seat_category)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatCategory.setAdapter(listsAdapter)
    }

    private fun insertSeatsRange() {
        val lists = resources.getStringArray(R.array.vip_seats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelection.setAdapter(listsAdapter)
        binding.editTextSeatSelectionStandardWindow.setAdapter(null)
        binding.editTextSeatSelectionStandardAlley.setAdapter(null)
    }

    private fun insertSeatsRange2() {
        val lists = resources.getStringArray(R.array.standard_windowSeats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelectionStandardWindow.setAdapter(listsAdapter)
        binding.editTextSeatSelection.setAdapter(null)
        binding.editTextSeatSelectionStandardAlley.setAdapter(null)
    }

    private fun insertSeatsRange3() {
        val lists = resources.getStringArray(R.array.standard_alleySeats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelectionStandardAlley.setAdapter(listsAdapter)
        binding.editTextSeatSelection.setAdapter(null)
        binding.editTextSeatSelectionStandardWindow.setAdapter(null)
    }

    private fun insertSeatsCoachesFour() {
        val lists = resources.getStringArray(R.array.four_coaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatCoachSelection.setAdapter(listsAdapter)
    }

    private fun insertSeatsCoachesFive() {
        val lists = resources.getStringArray(R.array.five_coaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatCoachSelection.setAdapter(listsAdapter)
    }

    private fun insertSeatData() {
        val trainName = binding.editTextSeatTrainName.text.toString()
        val trainCoach = binding.editTextSeatCoachSelection.text.toString()
        val available = "Yes"
        val reserved = "No"
        val seatPrice = binding.editTextSeatTrainPrice.text.toString()

        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainSeats")

        // for vip seats
        when (binding.editTextSeatSelection.text.toString()){
            "A1 - D1" -> {
                val arrayList = arrayListOf<String>("A1", "B1", "C1", "D1")

                for (i in 0..3) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
            "A2 - D2" -> {
                val arrayList = arrayListOf<String>("A2", "B2", "C2", "D2")

                for (i in 0..3) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
        }

        // for window standard
        when (binding.editTextSeatSelectionStandardWindow.text.toString()) {
            "A3 - A13" -> {
                val arrayList = arrayListOf<String>("A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13")

                for (i in 0..10) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
            "D3 - D13" -> {
                val arrayList = arrayListOf<String>("D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13")

                for (i in 0..10) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
        }

        //for standard alley seats
        when (binding.editTextSeatSelectionStandardAlley.text.toString()) {
            "B3 - B13" -> {
                val arrayList = arrayListOf<String>("B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13")

                for (i in 0..10) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
            "C3 - C13" -> {
                val arrayList = arrayListOf<String>("C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12", "C13")

                for (i in 0..10) {
                    val seats = SeatsData (
                        available, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i]).setValue(seats)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}