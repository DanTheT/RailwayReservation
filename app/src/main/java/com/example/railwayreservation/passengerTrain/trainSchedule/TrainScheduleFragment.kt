package com.example.railwayreservation.passengerTrain.trainSchedule

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.databinding.FragmentTrainScheduleBinding
import com.example.railwayreservation.passengerTrain.trainInfo.ParcelizedNameDest
import com.google.firebase.database.*
import com.example.railwayreservation.R

class TrainScheduleFragment : Fragment(), PassengerScheduleAdapter.OnItemClick {

    private var _binding: FragmentTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var lineDatabase: DatabaseReference
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<ScheduleData>
    private lateinit var navController: NavController
    private var deactivateDialog: AlertDialog? = null
    private val args by navArgs<TrainScheduleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainScheduleBinding.inflate(inflater, container, false)

        scheduleRecycleView= binding.passengerScheduleRecycler
        scheduleRecycleView.layoutManager = LinearLayoutManager(requireContext())

        scheduleArrayList = arrayListOf()

        binding.trainTypeText.text = args.name.trainName

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        try {
            retrieveScheduleTime(binding.trainTypeText.text.toString())
        }catch (e: Exception) {
            Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
        }

        binding.passengerTrainScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_trainScheduleFragment_to_trainInfoFragment)
        }
    }

    private fun retrieveScheduleTime(trainName: String){
        lineDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule").child(trainName)
        lineDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(scheduleSnapshot in snapshot.children){
                        val schedule = scheduleSnapshot.getValue(ScheduleData::class.java)
                        scheduleArrayList.add(schedule!!)
                    }
                    scheduleRecycleView.adapter = PassengerScheduleAdapter(scheduleArrayList, this@TrainScheduleFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onItemSelectClick(data: ScheduleData) {
        val railName: String = data.trainName
        val railStart: String = data.fromStation
        val railDestination = data.nextStation
        val railStatus = data.status

        val nameAndDest = ParcelizedNameDest (
            railName, railStart, railDestination
                )

        try {
            when(railStatus) {
                "Deactivate" -> {
                    displayDialog()
                }
                else -> {
                    val action = TrainScheduleFragmentDirections.actionTrainScheduleFragmentToPassengerSeatsFragment(nameAndDest)
                    Toast.makeText(requireContext(), "You have selected from ${data.fromStation} to ${data.nextStation} ", Toast.LENGTH_LONG).show()
                    findNavController().navigate(action)
                }
            }
        } catch (e: Exception) {
            val passengerScheduleTag = "TrainScheduleFragment"
            Log.d(passengerScheduleTag, "${e.message}")
        }
    }

    private fun displayDialog() {
        val aDialogBuilder = AlertDialog.Builder(requireContext())
        aDialogBuilder.setTitle("Attention")
        aDialogBuilder.setMessage("The selected schedule is currently unavailable")
        aDialogBuilder.setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int -> }
        deactivateDialog = aDialogBuilder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}