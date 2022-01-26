package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainSchedule.addSchedule.AddScheduleFragment
import com.example.railwayreservation.admin.trainSchedule.deleteSchedule.DeleteScheduleFragment
import com.example.railwayreservation.admin.trainSchedule.updateSchedule.UpdateScheduleFragment
import com.example.railwayreservation.databinding.FragmentScheduleManageBinding

class ScheduleManageFragment : Fragment() {

    private var _binding: FragmentScheduleManageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleManageBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            val toAddPage = activity as NavigationFrag
            toAddPage.navFrag(AddScheduleFragment(), addToStack = false)
        }

        binding.updateBtn.setOnClickListener {
            val toUpdatePage = activity as NavigationFrag
            toUpdatePage.navFrag(UpdateScheduleFragment(), addToStack = false)
        }

        binding.deleteBtn.setOnClickListener {
            val toDeletePage = activity as NavigationFrag
            toDeletePage.navFrag(DeleteScheduleFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}