package com.example.railwayreservation.passengerTrain.trainSeats

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentPassengerSeatsBinding
import com.example.railwayreservation.passenger.MakeReservations
import com.example.railwayreservation.passenger.PassengerPayment
import com.example.railwayreservation.passenger.TicketActivity
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
        binding.seatOrigin.text = args.trainNameDest.fromStation
        binding.seatDestination.text = args.trainNameDest.nextStation
        binding.seatReachTime.text = args.trainNameDest.reachTime
        binding.seatArriveTime.text = args.trainNameDest.arrivalTime


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

            }

        }

        binding.proceedReservation.setOnClickListener {

            try {
                if (binding.seatTrainType.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Please select a coach", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    startActivity(Intent(context, TicketActivity::class.java))

                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }

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



}
