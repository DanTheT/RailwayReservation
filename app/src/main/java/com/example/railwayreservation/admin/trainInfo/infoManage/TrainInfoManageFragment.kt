package com.example.railwayreservation.admin.trainInfo.infoManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainInfo.addTrain.AddTrainInfoFragment
import com.example.railwayreservation.admin.trainInfo.checkTrain.CheckTrainInfoFragment
import com.example.railwayreservation.admin.trainInfo.deleteTrain.DeleteTrainInfoFragment
import com.example.railwayreservation.admin.trainInfo.updateTrain.UpdateTrainInfoFragment
import com.example.railwayreservation.databinding.FragmentTrainInfoManageBinding


class TrainInfoManageFragment : Fragment() {

    private var _binding: FragmentTrainInfoManageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainInfoManageBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            val toAddPage = activity as NavigationFrag
            toAddPage.navFrag(AddTrainInfoFragment(), false)
        }

        binding.updateBtn.setOnClickListener {
            val toUpdatePage = activity as NavigationFrag
            toUpdatePage.navFrag(UpdateTrainInfoFragment(), addToStack = false)
        }

        binding.deleteBtn.setOnClickListener {
            val toDeletePage = activity as NavigationFrag
            toDeletePage.navFrag(DeleteTrainInfoFragment(), addToStack = false)
        }

        binding.checkInfoBtn.setOnClickListener {
            val toCheckPage = activity as NavigationFrag
            toCheckPage.navFrag(CheckTrainInfoFragment(), addToStack = false)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}