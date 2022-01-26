package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainSeats.addSeats.AddSeatsFragment
import com.example.railwayreservation.databinding.FragmentSeatsManageBinding

class SeatsManageFragment : Fragment() {

    private var _binding: FragmentSeatsManageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeatsManageBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            val toAddSeatsPage = activity as NavigationFrag
            toAddSeatsPage.navFrag(AddSeatsFragment(), addToStack = false)
        }

        return binding.root
    }


}