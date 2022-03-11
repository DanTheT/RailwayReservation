package com.example.railwayreservation.passengerTrain.trainSeats

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentPassengerSeatsBinding
import com.example.railwayreservation.passenger.PassengerPayment
import com.google.firebase.database.*

class PassengerSeatsFragment : Fragment() {

    private var _binding: FragmentPassengerSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var seatsDatabase: DatabaseReference
    private lateinit var seatsArrayList: ArrayList<PassengerSeatsData>
    private val args by navArgs<PassengerSeatsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPassengerSeatsBinding.inflate(inflater, container, false)

        binding.seatTrainType.text = args.trainNameDest.trainName
        binding.seatDestination.text = args.trainNameDest.fromStation
        binding.seatDestination2.text = args.trainNameDest.nextStation

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectSeatGetBtn.setOnClickListener {
            val getNameTrain = binding.seatTrainType.text.toString()

            seatsDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
            seatsDatabase.child(getNameTrain).child("car").get().addOnSuccessListener {

                when (it.value.toString()) {
                    "4 Coaches" -> {
                        insertSeatsCoachesFour()
                    }
                    "5 Coaches" -> {
                        insertSeatsCoachesFive()
                    }
                }

                insertSeatsCategory()
                insertSeatsRange()
//                "VIP seats" -> {
//                        insertVIPSeats()
//                    }
//                    "Standard seats - At Window" -> {
//                        insertStandardWindowSeats()
//                    }
//
//                    "Standard seats - At Alley" -> {
//                        insertStandardAlleySeats()
//                    }
//                }
            }

        }

        binding.confirmPayment.setOnClickListener {

//            try {
//                if (binding.seatTrainType.text.isEmpty()) {
//                    Toast.makeText(requireContext(), "No selected coach, category and seats", Toast.LENGTH_SHORT)
//                        .show()
//                } else {
//
//                }
//            } catch (e: Exception) {
//                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
//            }

            startActivity(Intent(context, PassengerPayment::class.java))

        }

        binding.passengerSeatsTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_passengerSeatsFragment_to_trainInfoFragment)
        }
    }


    private fun insertSeatsCoachesFive() {
        val lists = resources.getStringArray(R.array.four_coaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.selectSeatCoach.setAdapter(listsAdapter)
    }

    private fun insertSeatsCoachesFour() {
        val lists = resources.getStringArray(R.array.four_coaches)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.selectSeatCoach.setAdapter(listsAdapter)
    }

    private fun insertSeatsCategory() {
        val lists = resources.getStringArray(R.array.seat_category)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.selectSeatCategory.setAdapter(listsAdapter)

    }

    private fun insertSeatsRange() {
        val lists = resources.getStringArray(R.array.seats_range)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.selectSeatRange.setAdapter(listsAdapter)

    }


//    private fun insertVIPSeats() {
//        val lists = resources.getStringArray(R.array.vip_seats)
//
//        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
//        binding.selectSeatCategory.setAdapter(listsAdapter)
//
//    }
//
//    private fun insertStandardWindowSeats() {
//        val lists = resources.getStringArray(R.array.standard_windowSeats)
//
//        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
//        binding.selectSeatCategory.setAdapter(listsAdapter)
//
//    }
//
//    private fun insertStandardAlleySeats() {
//        val lists = resources.getStringArray(R.array.standard_alleySeats)
//
//        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
//        binding.selectSeatCategory.setAdapter(listsAdapter)
//
//    }

//        val lists = resources.getStringArray(R.array.vip_seats_price)
//        val lists = resources.getStringArray(R.array.standard_windowSeats_price)
//        val lists = resources.getStringArray(R.array.standard_alleySeats_price)


}
