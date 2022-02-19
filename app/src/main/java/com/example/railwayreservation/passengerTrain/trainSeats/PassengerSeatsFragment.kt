package com.example.railwayreservation.passengerTrain.trainSeats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentPassengerSeatsBinding
import com.google.firebase.database.*

class PassengerSeatsFragment : Fragment() {

    private var _binding: FragmentPassengerSeatsBinding? = null
    private val binding get() = _binding!!
    private var recipient: String? = null
    private lateinit var navController: NavController
    private lateinit var seatsDatabase: DatabaseReference
    private lateinit var seatsArrayList: ArrayList<PassengerSeatsData>
    private val args by navArgs<PassengerSeatsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPassengerSeatsBinding.inflate(inflater, container, false)

        binding.seatTrainType.text = args.trainNameDest.trainName
        binding.seatDestination.text = "${args.trainNameDest.fromStation} ${args.trainNameDest.nextStation}"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "$recipient"
        view.findViewById<TextView>(R.id.seat_trainType).text = message
        getSelectedSeatData(message)
    }

    private fun getSelectedSeatData(trainType: String){
        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainType).child("Seats")

        seatsDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (seatsSnapshot in snapshot.children) {
                        }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
