package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentBtmSheetSeatsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BtmSheetSeatsFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBtmSheetSeatsBinding? = null
    private val binding get() = _binding!!
    var coachNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coachNumber = requireArguments().getString("coachNumber")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtmSheetSeatsBinding.inflate(inflater, container, false)

        val coachNum = "$coachNumber"
        binding.btmSheetCoachNumber.text = coachNum

        val bundle = bundleOf("coachNumber" to coachNum)
        binding.updateTrainSeat.setOnClickListener {
            findNavController().navigate(R.id.action_btmSheetSeatsFragment_to_updateSeatsFragment, bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}