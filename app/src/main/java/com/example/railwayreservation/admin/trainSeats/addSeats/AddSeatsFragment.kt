package com.example.railwayreservation.admin.trainSeats.addSeats

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
import com.example.railwayreservation.admin.trainSeats.data.SeatsData
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
    ): View {
        _binding = FragmentAddSeatsBinding.inflate(inflater, container, false)

        insertNameTrain()
        insertSeatsCategory()

        pricingFocus()

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

                when (it.value.toString()) {
                    "4 Coaches" -> {
                        insertSeatsCoachesFour()
                    }
                    "5 Coaches" -> {
                        insertSeatsCoachesFive()
                    }
                }
            }

            when (binding.editTextSeatCategory.text.toString()) {
                "VIP seats" -> {
                    insertSeatsRangeVip()
                }

                "Standard seats - At Window" -> {
                    insertSeatsRangeWindow()
                }
                else -> {
                    insertSeatsRangeAlley()
                }
            }
        }

        binding.buttonAddSeats.setOnClickListener {
            val selectedCoachCat = binding.editTextSeatCategory.text.toString()
            val insertedSeatPrice = binding.editTextSeatTrainPrice.text.toString()
            try {
                when (selectedCoachCat) {
                    "VIP seats" -> {
                        if (insertedSeatPrice == "10") {
                            binding.seatTrainPriceLayout.helperText = null
                            insertSeatData()
                            Toast.makeText(requireContext(), "New seats added", Toast.LENGTH_SHORT).show()
                        } else {
                            binding.seatTrainPriceLayout.helperText = "Price should be '10' "
                        }
                    }
                    "Standard seats - At Window" -> {
                        if (insertedSeatPrice == "13") {
                            binding.seatTrainPriceLayout.helperText = null
                            insertSeatData()
                            Toast.makeText(requireContext(), "New seats added", Toast.LENGTH_SHORT).show()
                        } else {
                            binding.seatTrainNameLayout.helperText = "Price should be '13' "
                        }
                    }
                    else -> {
                        if (insertedSeatPrice == "15") {
                            binding.seatTrainPriceLayout.helperText = null
                            insertSeatData()
                            Toast.makeText(requireContext(), "New seats added", Toast.LENGTH_SHORT).show()
                        } else {
                            binding.seatTrainNameLayout.helperText = "Price should be '15' "
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pricingFocus() {
        binding.editTextSeatTrainPrice.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.seatTrainPriceLayout.helperText = null
            }
        }
    }

    private fun insertSeatsCategory() {
        val lists = resources.getStringArray(R.array.seat_category)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatCategory.setAdapter(listsAdapter)
    }

    private fun insertNameTrain() {
        val lists = resources.getStringArray(R.array.train_name_items)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatCategory.setAdapter(listsAdapter)
    }

    private fun insertSeatsRangeVip() {
        val lists = resources.getStringArray(R.array.vip_seats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelection.setAdapter(listsAdapter)
    }

    private fun insertSeatsRangeWindow() {
        val lists = resources.getStringArray(R.array.standard_windowSeats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelection.setAdapter(listsAdapter)
    }

    private fun insertSeatsRangeAlley() {
        val lists = resources.getStringArray(R.array.standard_alleySeats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.editTextSeatSelection.setAdapter(listsAdapter)
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
        when (binding.editTextSeatSelection.text.toString()) {
            "A1 - D1" -> {
                val arrayList = arrayListOf("A1", "B1", "C1", "D1")

                for (i in 0..3) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
            "A2 - D2" -> {
                val arrayList = arrayListOf("A2", "B2", "C2", "D2")

                for (i in 0..3) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
        }

        // for window standard
        when (binding.editTextSeatSelection.text.toString()) {
            "A3 - A13" -> {
                val arrayList = arrayListOf(
                    "A3",
                    "A4",
                    "A5",
                    "A6",
                    "A7",
                    "A8",
                    "A9",
                    "A10",
                    "A11",
                    "A12",
                    "A13"
                )

                for (i in 0..10) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
            "D3 - D13" -> {
                val arrayList = arrayListOf(
                    "D3",
                    "D4",
                    "D5",
                    "D6",
                    "D7",
                    "D8",
                    "D9",
                    "D10",
                    "D11",
                    "D12",
                    "D13"
                )

                for (i in 0..10) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
        }

        //for standard alley seats
        when (binding.editTextSeatSelection.text.toString()) {
            "B3 - B13" -> {
                val arrayList = arrayListOf(
                    "B3",
                    "B4",
                    "B5",
                    "B6",
                    "B7",
                    "B8",
                    "B9",
                    "B10",
                    "B11",
                    "B12",
                    "B13"
                )

                for (i in 0..10) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
            "C3 - C13" -> {
                val arrayList = arrayListOf(
                    "C3",
                    "C4",
                    "C5",
                    "C6",
                    "C7",
                    "C8",
                    "C9",
                    "C10",
                    "C11",
                    "C12",
                    "C13"
                )

                for (i in 0..10) {
                    val seats = SeatsData(
                        available, trainCoach, arrayList[i], reserved, seatPrice
                    )
                    seatsDatabase.child(trainName).child(trainCoach).child(arrayList[i])
                        .setValue(seats)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}