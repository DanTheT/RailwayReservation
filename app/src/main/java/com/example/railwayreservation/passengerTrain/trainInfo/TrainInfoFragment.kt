package com.example.railwayreservation.passengerTrain.trainInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TrainInfoFragment : Fragment() {

    private var _binding: FragmentTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainInfoDb: DatabaseReference
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainInfoBinding.inflate(inflater, container, false)

        preloadTrainType()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.searchButton.setOnClickListener {
            val selectTrain = binding.trainTypeSpinner.text.toString()
            checkTrainLine(selectTrain)
        }

        val trainNameSelect = binding.trainTypeSpinner.text.toString()
        binding.selectionBtn.setOnClickListener {
            val bundle = bundleOf("recipient" to trainNameSelect)
            navController.navigate(R.id.action_trainInfoFragment_to_trainScheduleFragment, bundle)
        }
    }

    private fun checkTrainLine(trainName: String){
        trainInfoDb = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
        trainInfoDb.child(trainName).get().addOnSuccessListener {
            if(it.exists()){
                val trainLine = it.child("trainLine").value
                val startStation = it.child("startStation").value
                val endStation = it.child("endStation").value
                val trainStatus = it.child("status").value

                binding.trainLineTextview.text = trainLine.toString()
                binding.trainStartTextview.text = startStation.toString()
                binding.trainEndTextview.text = endStation.toString()
                binding.trainStatusTextview.text = trainStatus.toString()
            }
        }

    }

    private fun preloadTrainType(){
        val lists = resources.getStringArray(R.array.train_name_items)

        val listAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.trainTypeSpinner.setAdapter(listAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}