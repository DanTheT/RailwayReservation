package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentUpdateSeatsBinding

class UpdateSeatsFragment : Fragment() {

    private var _binding: FragmentUpdateSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var coachNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coachNumber = requireArguments().getString("coachNumber")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateSeatsBinding.inflate(inflater, container, false)

        val coachNum = "$coachNumber"
        binding.textViewCoach.text = coachNum

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainSeatMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateSeatsFragment_to_overallTrainSeatsFragment)
        }
    }
}