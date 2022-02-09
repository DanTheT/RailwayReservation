package com.example.railwayreservation.admin.trainSeats

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
import com.example.railwayreservation.databinding.FragmentOverallTrainSeatsBinding
import com.google.firebase.database.*

class OverallTrainSeatsFragment : Fragment() {

    private var _binding: FragmentOverallTrainSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var seatsRecycleView: RecyclerView
    private lateinit var seatsArrayList: ArrayList<SeatsData>
    private lateinit var seatsDb: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverallTrainSeatsBinding.inflate(inflater, container, false)
        seatsRecycleView = binding.displayOverallSeatsRV
        seatsRecycleView.layoutManager = LinearLayoutManager(context)
        seatsArrayList = arrayListOf()

        insertTrainName()

        try {
            binding.searchSeatName.setOnClickListener {
                val name = binding.textFieldSearchSeatName.text.toString()
                getSeatsData(name)
            }
        }catch (e: Exception) {
            e.message
        }

        binding.clearRecyclerSeatName.setOnClickListener {
            try {
                if (seatsRecycleView.isShown) {
                    seatsArrayList.clear()
                    seatsRecycleView.adapter?.notifyDataSetChanged()
                }
            }catch (e: Exception) {
                e.message
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.overallTrainSeatMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainSeatsFragment_to_trainManageFragment)
        }

        binding.fltAddNewSeats.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainSeatsFragment_to_addSeatsFragment)
        }
    }

    private fun insertTrainName() {
        val lists = resources.getStringArray(R.array.train_name_items)

        val listAdapter = ArrayAdapter(requireContext(), R.layout.list_for_dropdown, lists)
        binding.textFieldSearchSeatName.setAdapter(listAdapter)
    }

    private fun getSeatsData(trainName: String) {
        seatsDb = FirebaseDatabase.getInstance().getReference("TrainSeats")
        for (i in 1..4) {
            seatsDb.child(trainName).child("Coach $i").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (seatSnap in snapshot.children) {
                            val seats = seatSnap.getValue(SeatsData::class.java)
                            seatsArrayList.add(seats!!)
                        }
                        seatsRecycleView.adapter = SeatsAdapter(seatsArrayList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}