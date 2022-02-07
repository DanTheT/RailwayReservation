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
import com.example.railwayreservation.databinding.FragmentOverallTrainSeatsBinding

class OverallTrainSeatsFragment : Fragment() {

    private var _binding: FragmentOverallTrainSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOverallTrainSeatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.overallTrainSeatMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainSeatsFragment_to_trainManageFragment)
        }

        binding.fltAddNewSeats.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainSeatsFragment_to_addSeatsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}