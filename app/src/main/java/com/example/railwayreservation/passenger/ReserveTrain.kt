package com.example.railwayreservation.passenger

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ListAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityReserveTrainBinding
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReserveTrain : AppCompatActivity() {

    private lateinit var trainInfoDb: DatabaseReference
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var _binding: ActivityReserveTrainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = ActivityReserveTrainBinding.inflate(inflater, container, false)

            preloadTrainType()

            return binding.root
        }

            binding.searchButton.setOnClickListener {
                val selectTrain = binding.trainTypeSpinner.text.toString()
                checkTrainLine(selectTrain)
            }

            val trainNameSelect = binding.trainTypeSpinner.text.toString()
            binding.selectionBtn.setOnClickListener {
                val bundle = bundleOf("recipient" to trainNameSelect)
                startActivity(Intent(this,ReserveSchedule::class.java))


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

            val listAdapter = ArrayAdapter(
                requireContext(),
                R.layout.list_for_dropdown,
                lists
            )
            binding.trainTypeSpinner.setAdapter(listAdapter as ListAdapter?)
        }

    private fun ArrayAdapter(requireContext: Unit, listForDropdown: Int, lists: Array<String>) {

    }

    private fun requireContext() {

    }


    fun onDestroyView() {
        _binding = null
    }

    }

private fun AutoCompleteTextView.setAdapter(listAdapter: ListAdapter?) {

}


