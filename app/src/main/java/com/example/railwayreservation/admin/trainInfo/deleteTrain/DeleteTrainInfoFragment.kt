package com.example.railwayreservation.admin.trainInfo.deleteTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.trainInfo.infoManage.TrainInfoManageFragment
import com.example.railwayreservation.databinding.FragmentDeleteTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteTrainInfoFragment : Fragment() {

    private var _binding: FragmentDeleteTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var deleteDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeleteTrainInfoBinding.inflate(inflater, container, false)

        insertTrainTypeValue()

        binding.deleteInfoBtn.setOnClickListener {
            var trainType = binding.deleteTrainSpinner.selectedItem.toString()

            if(trainType.isNotEmpty()){
                deleteSelectedInfo(trainType)
            }else
                Toast.makeText(context, "Please select a value", Toast.LENGTH_SHORT).show()
        }

        binding.backInfoDeleteBtn.setOnClickListener {
            val backDeletePage = activity as NavigationFrag
            backDeletePage.navFrag(TrainInfoManageFragment(), addToStack = false)
        }

        return binding.root
    }

    private fun deleteSelectedInfo(trainType: String) {
        deleteDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo")
        deleteDatabase.child(trainType).removeValue().addOnSuccessListener {
            Toast.makeText(context, "Selected info successful deleted $trainType", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertTrainTypeValue(){
        val spinner: Spinner = binding.deleteTrainSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.train_type_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}