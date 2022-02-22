package com.example.railwayreservation.passengerTrain.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentPassengerSeatsBinding
import com.google.firebase.database.*

class PassengerSeatsFragment : Fragment() {

    private var _binding: FragmentPassengerSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
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

                when(it.value.toString()) {
                    "4 Coaches" -> {
                        insertSeatsCoachesFour()
                    }
                    "5 Coaches" -> {
                        insertSeatsCoachesFive()
                    }
                }
            }
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

}
