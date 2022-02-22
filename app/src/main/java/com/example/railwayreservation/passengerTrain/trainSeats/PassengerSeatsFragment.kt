package com.example.railwayreservation.passengerTrain.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
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

        try {
            getSelectedSeatData(binding.seatTrainType.text.toString())
        }catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSelectedSeatData(trainType: String) {
        seatsDatabase =
            FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainType).child("Seats")

        seatsDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
