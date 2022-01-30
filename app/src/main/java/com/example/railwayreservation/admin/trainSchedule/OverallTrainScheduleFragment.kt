package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentOverallTrainScheduleBinding
import com.google.firebase.database.*

class OverallTrainScheduleFragment : Fragment() {

    private var _binding: FragmentOverallTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<TrainSchedule>
    private lateinit var scheduleDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverallTrainScheduleBinding.inflate(inflater, container, false)

        scheduleRecycleView= binding.displayScheduleRV
        scheduleRecycleView.layoutManager = LinearLayoutManager(context)

        scheduleArrayList = arrayListOf<TrainSchedule>()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getScheduleTime()
    }

    private fun getScheduleTime(){
        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo").child("Schedule")
        scheduleDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(scheduleSnap in snapshot.children){
                        val schedule = scheduleSnap.getValue(TrainSchedule::class.java)
                        scheduleArrayList.add(schedule!!)
                    }
                    scheduleRecycleView.adapter = TrainScheduleAdapter(scheduleArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}