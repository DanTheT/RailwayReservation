package com.example.railwayreservation.admin.reports

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.OpReportsFragmentBinding

class OpReportsFragment : Fragment() {

    private var _binding: OpReportsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = OpReportsFragment()
    }

    private lateinit var viewModel: OpReportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OpReportsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OpReportsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.overallReportOperationTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_adminRegisterFragment_to_adminLoginFragment)
        }
    }

}