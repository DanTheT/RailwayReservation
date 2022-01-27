package com.example.railwayreservation.admin.trainSeats.addSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainSeats.SeatsManageFragment
import com.example.railwayreservation.databinding.FragmentAddSeatsBinding
import com.google.firebase.database.*

class AddSeatsFragment : Fragment() {

    private var _binding: FragmentAddSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var seatsDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSeatsBinding.inflate(inflater, container, false)

        binding.backSeatsManageBtn.setOnClickListener {
            val backSeatsPage = activity as NavigationFrag
            backSeatsPage.navFrag(SeatsManageFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainName = binding.editTextSeatTrainName.text.toString()
        val seatNo = binding.editTextSeatNumber.text.toString()

        binding.buttonAddSeats.setOnClickListener {
            addSeatsData(trainName,seatNo)
        }
    }

    private fun addSeatsData(trainName: String, seatNo: String){
        val testString = "available"
        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}