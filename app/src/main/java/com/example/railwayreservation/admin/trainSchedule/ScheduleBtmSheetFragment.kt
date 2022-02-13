package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentScheduleBtmSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScheduleBtmSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentScheduleBtmSheetBinding? = null
    private val binding get() = _binding!!
    var trainName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainName = requireArguments().getString("trainName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBtmSheetBinding.inflate(inflater, container, false)

        val trainName = "$trainName"
        binding.btmSheetTrainNameSchedule.text = trainName

        val bundle = bundleOf("trainName" to trainName)
        binding.updateTrainScheduleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleBtmSheetFragment_to_updateScheduleFragment, bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}