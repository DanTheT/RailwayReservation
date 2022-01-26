package com.example.railwayreservation.admin.trainInfo.updateTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainInfo.infoManage.TrainInfoManageFragment
import com.example.railwayreservation.databinding.FragmentUpdateTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateTrainInfoFragment : Fragment() {

    private var _binding: FragmentUpdateTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var infoDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTrainInfoBinding.inflate(inflater, container, false)

        binding.updateInfoBtn.setOnClickListener {
            val trainType = binding.updateTrainTypeTxt.text.toString()
            val trainLane = binding.updateTrainLaneTxt.text.toString()
            val trainStartStation = binding.updateStartStationTxt.text.toString()
            val trainEndStation = binding.updateEndStationTxt.text.toString()
            val trainCoachNum = binding.updateTrainCoachNo.text.toString()
            val trainNumber = binding.updateTrainNumber.text.toString()

            infoDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo")

            val info = mapOf<String,String>(
                "trainLine" to trainLane,
                "startStation" to trainStartStation,
                "endStation" to trainEndStation,
                "trainNum" to trainNumber,
                "Car" to trainCoachNum
            )

            infoDatabase.child(trainType).updateChildren(info).addOnSuccessListener {
                binding.updateTrainTypeTxt.text.clear()
                binding.updateTrainLaneTxt.text.clear()
                binding.updateStartStationTxt.text.clear()
                binding.updateEndStationTxt.text.clear()
                binding.updateTrainNumber.text.clear()
                binding.updateTrainCoachNo.text.clear()
                Toast.makeText(context, "Update Successful to $trainType", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backPageBtn.setOnClickListener {
            val backInfoPage = activity as NavigationFrag
            backInfoPage.navFrag(TrainInfoManageFragment(), addToStack = false)
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