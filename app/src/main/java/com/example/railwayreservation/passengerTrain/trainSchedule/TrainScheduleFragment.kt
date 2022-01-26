package com.example.railwayreservation.passengerTrain.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainScheduleBinding
import com.google.firebase.database.*

class TrainScheduleFragment : Fragment(), PassengerScheduleAdapter.OnItemClick {

    private var _binding: FragmentTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var lineDatabase: DatabaseReference
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<ScheduleData>
    private lateinit var navController: NavController
    var recipient: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainScheduleBinding.inflate(inflater, container, false)

        scheduleRecycleView= binding.passengerScheduleRecycler
        scheduleRecycleView.layoutManager = LinearLayoutManager(requireContext())

        scheduleArrayList = arrayListOf<ScheduleData>()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val message = "$recipient"
        view.findViewById<TextView>(R.id.trainTypeText).text = message
        getSelectedData(message)
        retrieveScheduleTime(message)

    }

    private fun getSelectedData(trainType: String){
        lineDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo")
        lineDatabase.child(trainType).get().addOnSuccessListener {
            if (it.exists()){
                val startStation = it.child("startStation").value
                binding.scheduleStartStationText.text = startStation.toString()

                val endStation = it.child("endStation").value
                binding.scheduleEndStationText.text = endStation.toString()
            }
        }
    }

    private fun retrieveScheduleTime(trainType: String){
        lineDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child(trainType).child("Schedule")
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
        Toast.makeText(requireContext(), "Clicked item ${data.fromStation}", Toast.LENGTH_LONG).show()
        val bundle = bundleOf("recipient" to recipient.toString())
        navController.navigate(R.id.action_trainScheduleFragment_to_passengerSeatsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}