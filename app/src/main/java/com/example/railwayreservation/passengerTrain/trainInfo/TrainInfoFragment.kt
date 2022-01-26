package com.example.railwayreservation.passengerTrain.trainInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TrainInfoFragment : Fragment(), View.OnClickListener {

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

        binding.searchButton.setOnClickListener {
            val selectType = binding.trainTypeSpinner.selectedItem.toString()
            checkTrainLine(selectType)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.selection_btn).setOnClickListener(this)
    }

    private fun checkTrainLine(trainType: String){
        trainInfoDb = FirebaseDatabase.getInstance().getReference("TrainInfo")
        trainInfoDb.child(trainType).get().addOnSuccessListener {
            if(it.exists()){
                val endStation = it.child("endStation").value
                val startStation = it.child("startStation").value
                val trainLine = it.child("trainLine").value
                val trainNum = it.child("trainNum").value

                binding.trainStartTextview.text = startStation.toString()
                binding.trainEndTextview.text = endStation.toString()
                binding.trainLineTextview.text = trainLine.toString()
                binding.trainNumTextview.text = trainNum.toString()
            }
        }

    }

    private fun preloadTrainType(){
        val spinner: Spinner = binding.trainTypeSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.trainType_items,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        val trainTypeBtn = binding.trainTypeSpinner.selectedItem
        when(v!!.id){
            R.id.selection_btn -> {
                if(trainTypeBtn != null){
                    val bundle = bundleOf("recipient" to trainTypeBtn.toString())
                    navController.navigate(R.id.action_trainInfoFragment_to_trainScheduleFragment, bundle)
                }
            }
        }
    }
}