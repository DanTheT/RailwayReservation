package com.example.railwayreservation.admin.trainManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainManageBinding

class TrainManageFragment : Fragment(), View.OnClickListener {

    private lateinit var _binding: FragmentTrainManageBinding
    private val binding get() = _binding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainManageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.train_schedule_btn).setOnClickListener(this)

        binding.trainInfoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_trainManageFragment_to_overallTrainInfoFragment)
        }

        binding.trainManageTopAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_trainManageFragment_to_adminMainFragment)
        }

        binding.trainSeatsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_trainManageFragment_to_overallTrainSeatsFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.train_schedule_btn -> {
                findNavController().navigate(R.id.action_trainManageFragment_to_overallTrainScheduleFragment)
            }
        }
    }
}