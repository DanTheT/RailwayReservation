package com.example.railwayreservation.admin.trainManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainInfo.infoManage.TrainInfoManageFragment
import com.example.railwayreservation.admin.trainSchedule.ScheduleManageFragment
import com.example.railwayreservation.admin.trainSeats.SeatsManageFragment
import com.example.railwayreservation.databinding.FragmentTrainManageBinding

class TrainManageFragment : Fragment() {

    private lateinit var _binding: FragmentTrainManageBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainManageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trainInfoBtn.setOnClickListener {
            val infoManagePage = activity as NavigationFrag
            infoManagePage.navFrag(TrainInfoManageFragment(), false)
        }

        binding.trainScheduleBtn.setOnClickListener {
            val scheduleManagePage = activity as NavigationFrag
            scheduleManagePage.navFrag(ScheduleManageFragment(), addToStack = false)
        }

        binding.trainSeatsBtn.setOnClickListener {
            val seatsManagePage = activity as NavigationFrag
            seatsManagePage.navFrag(SeatsManageFragment(), addToStack = false)
        }
    }
}