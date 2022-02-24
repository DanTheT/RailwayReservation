package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteSeatsBinding.inflate(inflater, container, false)

        binding.textViewCoach.text = args.receiveCoachNUm.coachNum

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainSeatMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateSeatsFragment_to_overallTrainSeatsFragment)
        }
    }

    private fun changeSeatStatus() {

    }
}