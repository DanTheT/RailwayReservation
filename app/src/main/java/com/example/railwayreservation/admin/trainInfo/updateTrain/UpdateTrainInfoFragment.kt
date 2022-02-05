package com.example.railwayreservation.admin.trainInfo.updateTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentUpdateTrainInfoBinding

class UpdateTrainInfoFragment : Fragment() {

    private var _binding: FragmentUpdateTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    var trainName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainName = requireArguments().getString("trainName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTrainInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.updateTrainInfoMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_updateTrainInfoFragment_to_overallTrainInfoFragment)
        }

        val trainName = "$trainName"
        binding.textFieldEditTrainName.setText(trainName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}