package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentDeleteSeatsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteSeatsFragment : Fragment() {

    private var _binding: FragmentDeleteSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var seatsDatabase: DatabaseReference
    private val args by navArgs<DeleteSeatsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteSeatsBinding.inflate(inflater, container, false)

        binding.textViewCoach.text = args.receiveCoachNUm.coachNum
        binding.textViewNameTrainSeat.text = args.receiveCoachNUm.trainName
        binding.textViewSeatNoChange.text = args.receiveCoachNUm.seatNo

        insertSeatsStatus()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainSeatMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateSeatsFragment_to_overallTrainSeatsFragment)
        }

        binding.buttonChangeStatus.setOnClickListener {
            try {
                changeStatus()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertSeatsStatus() {
        val lists = resources.getStringArray(R.array.seatStatus)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textViewStatus.setAdapter(listsAdapter)
    }

    private fun changeStatus() {
        val coach = binding.textViewCoach.text.toString()
        val tName = binding.textViewNameTrainSeat.text.toString()
        val tSeatNo = binding.textViewSeatNoChange.text.toString()
        val getStatus = binding.textViewStatus.text.toString()

        seatsDatabase = FirebaseDatabase.getInstance().getReference("TrainSeats")
        seatsDatabase.child(tName).child(coach).child(tSeatNo).child("available").setValue(getStatus)
    }
}