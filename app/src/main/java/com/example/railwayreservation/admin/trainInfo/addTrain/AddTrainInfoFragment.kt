package com.example.railwayreservation.admin.trainInfo.addTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.TrainInfo
import com.example.railwayreservation.databinding.FragmentAddTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTrainInfoFragment : Fragment() {

    private var _binding: FragmentAddTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTrainInfoBinding.inflate(inflater, container, false)

        insertTrainCoach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertTrainStartStation()
        insertTrainEndStation()

        binding.addTrainBtn.setOnClickListener {
            addInfo()
        }
    }

    private fun insertTrainCoach(){
        val spinner: Spinner = binding.addCoachNumSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.numOfTrainCar,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun insertTrainStartStation() {
        val startSpinner: Spinner = binding.addTrainInfoStartSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.station_names,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            startSpinner.adapter = adapter
        }
    }

    private fun insertTrainEndStation() {
        val endSpinner: Spinner = binding.addTrainInfoEndSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.station_names,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            endSpinner.adapter = adapter
        }
    }

    private fun addInfo(){
        val trainType = binding.addTrainTypeTxt.text.toString()
        val trainLane = binding.addTrainLaneTxt.text.toString()
        val noOfCar = binding.addCoachNumSpinner.selectedItem.toString()
        val trainNum = binding.addTrainNoTxt.text.toString()
        val startStation = binding.addTrainInfoStartSpinner.selectedItem.toString()
        val endStation = binding.addTrainInfoEndSpinner.selectedItem.toString()

        trainDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo")
        val trainInfos = TrainInfo(
            trainLane, noOfCar, trainNum, startStation, endStation
        )

        trainDatabase.child(trainType).setValue(trainInfos).addOnSuccessListener {
            binding.addTrainTypeTxt.text.clear()
            binding.addTrainLaneTxt.text.clear()
            binding.addTrainNoTxt.text.clear()

            Toast.makeText(context, "Add Successful, New Train $trainType", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}