package com.example.railwayreservation.passengerTrain.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.databinding.FragmentTrainScheduleBinding
import com.example.railwayreservation.passengerTrain.trainInfo.ParcelizedNameDest
import com.google.firebase.database.*

class TrainScheduleFragment : Fragment(), PassengerScheduleAdapter.OnItemClick {

    private var _binding: FragmentTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var lineDatabase: DatabaseReference
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<ScheduleData>
    private lateinit var navController: NavController
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

        val nameAndDest = ParcelizedNameDest (
            railName, railStart, railDestination
                )

        try {
            val action = TrainScheduleFragmentDirections.actionTrainScheduleFragmentToPassengerSeatsFragment(nameAndDest)
            findNavController().navigate(action)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(requireContext(), "Clicked item ${data.fromStation}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}