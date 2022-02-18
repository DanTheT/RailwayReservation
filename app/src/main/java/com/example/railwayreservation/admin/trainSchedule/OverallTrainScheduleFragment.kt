package com.example.railwayreservation.admin.trainSchedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainSchedule.data.Schedule
import com.example.railwayreservation.admin.trainSchedule.data.ScheduleAdapter
import com.example.railwayreservation.databinding.FragmentOverallTrainScheduleBinding
import com.google.firebase.database.*

class OverallTrainScheduleFragment : Fragment(), ScheduleAdapter.OnItemClick {

    private var _binding: FragmentOverallTrainScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var scheduleRecycleView: RecyclerView
    private lateinit var scheduleArrayList: ArrayList<Schedule>
    private lateinit var scheduleDatabase: DatabaseReference
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

            try {
                if (binding.textFieldSearchName.text.isEmpty()) {
                    Toast.makeText(requireContext(), "No selected train name", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    getScheduleTime(name)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.clearRecycler.setOnClickListener {
            val selectName = binding.textFieldSearchName.text
            try {
                if (scheduleRecycleView.isShown) {
                    scheduleArrayList.clear()
                    selectName.clear()
                    scheduleRecycleView.adapter?.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
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
                    scheduleRecycleView.adapter =
                        ScheduleAdapter(scheduleArrayList, this@OverallTrainScheduleFragment)
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

    override fun onItemClick(data: Schedule) {
        val bundle = bundleOf("trainName" to data.trainName)
        findNavController().navigate(
            R.id.action_overallTrainScheduleFragment_to_scheduleBtmSheetFragment,
            bundle
        )
    }
}