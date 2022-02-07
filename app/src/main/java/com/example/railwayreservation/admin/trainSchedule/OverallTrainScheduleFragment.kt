package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentOverallTrainScheduleBinding
import com.google.firebase.database.*

class OverallTrainScheduleFragment : Fragment() {

    private var _binding: FragmentOverallTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<Schedule>
    private lateinit var scheduleDatabase: DatabaseReference
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverallTrainScheduleBinding.inflate(inflater, container, false)

        scheduleRecycleView = binding.displayScheduleRV
        scheduleRecycleView.layoutManager = LinearLayoutManager(context)

        scheduleArrayList = arrayListOf()

        insertScheduleTrainName()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.overallTrainScheduleMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainScheduleFragment_to_trainManageFragment)
        }

        binding.fltAddNewSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainScheduleFragment_to_addNewScheduleFragment)
        }

        binding.searchName.setOnClickListener {
            val name = binding.textFieldSearchName.text.toString()
            getScheduleTime(name)
        }

        binding.clearRecycler.setOnClickListener {
            try {
                if (scheduleRecycleView.isShown) {
                    scheduleArrayList.clear()
                    scheduleRecycleView.adapter?.notifyDataSetChanged()
                }
            }catch (e: Exception) {
                e.message
            }
        }
    }

    private fun insertScheduleTrainName() {
        val lists = resources.getStringArray(R.array.train_name_items)

        val listsAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldSearchName.setAdapter(listsAdapter)
    }

    private fun getScheduleTime(name: String) {

        scheduleDatabase = FirebaseDatabase.getInstance().getReference("TrainSchedule").child(name)
        scheduleDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (scheduleSnap in snapshot.children) {
                        val schedule = scheduleSnap.getValue(Schedule::class.java)
                        scheduleArrayList.add(schedule!!)
                    }
                    scheduleRecycleView.adapter = ScheduleAdapter(scheduleArrayList)
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