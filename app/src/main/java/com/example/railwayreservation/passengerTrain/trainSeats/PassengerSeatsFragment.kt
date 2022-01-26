package com.example.railwayreservation.passengerTrain.trainSeats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPassengerSeatsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "$recipient"
        view.findViewById<TextView>(R.id.seat_trainType).text = message
        getSelectedSeatData(message)
    }

    private fun getSelectedSeatData(trainType: String){
        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainType).child("Seats").child("car1")

        seatsDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (seatsSnapshot in snapshot.children) {
                        val seats = seatsSnapshot.getValue(PassengerSeatsData::class.java)

                        when (seats?.available) {
                            "Yes" -> binding.seatA1.setBackgroundColor(Color.rgb(0, 255, 0))
                            else -> {
                                binding.seatA1.setBackgroundColor(Color.rgb(255, 0, 0))
                            }
                        }

                        when (seats?.available) {
                            "Yes" -> binding.seatA2.setBackgroundColor(Color.rgb(0, 255, 0))
                            else -> {
                                binding.seatA2.setBackgroundColor(Color.rgb(255, 0, 0))
                            }
                        }

                        when (seats?.available) {
                            "Yes" -> binding.seatA3.setBackgroundColor(Color.rgb(0, 255, 0))
                            else -> {
                                binding.seatA3.setBackgroundColor(Color.rgb(255, 0, 0))
                            }
                        }

                        when (seats?.available) {
                            "Yes" -> binding.seatA4.setBackgroundColor(Color.rgb(0, 255, 0))
                            else -> {
                                binding.seatA4.setBackgroundColor(Color.rgb(255, 0, 0))
                            }
                        }

                        when (seats?.available) {
                            "Yes" -> binding.seatA5.setBackgroundColor(Color.rgb(0, 255, 0))
                            else -> {
                                binding.seatA5.setBackgroundColor(Color.rgb(255, 0, 0))
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
