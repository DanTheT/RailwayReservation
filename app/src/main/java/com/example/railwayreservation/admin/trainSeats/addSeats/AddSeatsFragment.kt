package com.example.railwayreservation.admin.trainSeats.addSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            when(binding.editTextSeatCategory.text.toString()) {
                "VIP seats" -> {
                    insertSeatsRange()
                }
                "Standard seats - At Window" -> {
                    insertSeatsRange2()
                }
                else -> {
                    insertSeatsRange3()
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

    private fun insertSeatData() {
        val trainName = binding.editTextSeatTrainName.text.toString()
        val available = "Yes"
        val reserved = "No"
        val seatPrice = binding.editTextSeatTrainPrice.text.toString()

        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainSeats")

        when (binding.editTextSeatSelection.text.toString()){
            "A1 - D1" -> {
                val arrayList = arrayListOf<String>("A1", "B1", "C1", "D1")
                val seats = SeatsData (
                    available, arrayList, reserved, seatPrice
                        )
                for (i in 0..3) {
                    seatsDatabase.child(trainName).child(arrayList[i]).setValue(seats)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}