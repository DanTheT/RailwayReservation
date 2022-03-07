package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainSchedule.data.ParcelizedSchedule
import com.example.railwayreservation.databinding.FragmentScheduleBtmSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScheduleBtmSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentScheduleBtmSheetBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ScheduleBtmSheetFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBtmSheetBinding.inflate(inflater, container, false)

        binding.btmSheetTrainNameSchedule.text = args.name.trainName
        binding.btmSheetTrainOrigin.text = args.name.fromStation
        binding.btmSheetTrainDest.text = args.name.nextStation
        binding.btmSheetTrainArrive.text = args.name.arriveTime
        binding.btmSheetReachTime.text = args.name.reachTime
        binding.btmSheetStatusTime.text = args.name.status

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateTrainScheduleBtn.setOnClickListener {
            val receiveName = binding.btmSheetTrainNameSchedule.text.toString()
            val receiveOri = binding.btmSheetTrainOrigin.text.toString()
            val receiveDest = binding.btmSheetTrainDest.text.toString()
            val receiveArrive = binding.btmSheetTrainArrive.text.toString()
            val receiveReach = binding.btmSheetReachTime.text.toString()
            val receiveStatus = binding.btmSheetStatusTime.text.toString()

            val schedule = ParcelizedSchedule (
                receiveName, receiveOri, receiveArrive,receiveDest, receiveReach, receiveStatus
            )
            val action = ScheduleBtmSheetFragmentDirections.actionScheduleBtmSheetFragmentToUpdateScheduleFragment(schedule)
            findNavController().navigate(action)
        }
    }
}