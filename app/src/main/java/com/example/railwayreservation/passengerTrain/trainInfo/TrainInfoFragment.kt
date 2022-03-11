package com.example.railwayreservation.passengerTrain.trainInfo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.example.railwayreservation.passenger.PassengerDatePicker
import com.example.railwayreservation.passenger.PassengerReservation
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TrainInfoFragment : Fragment() {

    private var _binding: FragmentTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainInfoDb: DatabaseReference
    private lateinit var navController: NavController
    private var deactiveDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainInfoBinding.inflate(inflater, container, false)

        preloadTrainType()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.searchButton.setOnClickListener {
            val selectTrain = binding.trainTypeSpinner.text.toString()

            try {
                if (binding.trainTypeSpinner.text.isEmpty()) {
                    Toast.makeText(requireContext(), "No selected train name", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    checkTrainLine(selectTrain)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }


        binding.selectionBtn.setOnClickListener {
            val name: String = binding.trainTypeSpinner.text.toString()

            try {
                if (binding.trainTypeSpinner.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Please select a train name", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    when(binding.trainStatusTextview.text.toString()) {
                        "Deactivate" -> {
                            displayDialog()
                        }
                        else -> {
                            val trainN = TrainName (
                                name

                            )
                            val intent = Intent(context, PassengerReservation::class.java)
                            intent.putExtra("Train Name : ", name)

                            val action = TrainInfoFragmentDirections.actionTrainInfoFragmentToTrainScheduleFragment(trainN)
                            findNavController().navigate(action)
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.passengerMainTopAppBar.setOnClickListener {
            startActivity(Intent(requireContext(), PassengerDatePicker::class.java))
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

    private fun displayDialog() {
        val aDialogBuilder = AlertDialog.Builder(requireContext())
        aDialogBuilder.setTitle("Attention")
        aDialogBuilder.setMessage("The current train selected is not available")
        aDialogBuilder.setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int -> }
        deactiveDialog = aDialogBuilder.show()
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