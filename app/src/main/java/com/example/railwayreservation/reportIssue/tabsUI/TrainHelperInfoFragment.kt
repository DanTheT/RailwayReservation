package com.example.railwayreservation.reportIssue.tabsUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainHelperInfoBinding

class TrainHelperInfoFragment : Fragment() {

    private var _binding: FragmentTrainHelperInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainHelperInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


}