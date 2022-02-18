package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentOverallTrainSeatsBinding
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class OverallTrainSeatsFragment : Fragment(), SeatsAdapter.OnItemClick {

    private var _binding: FragmentOverallTrainSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var seatsRecycleView: RecyclerView
    private lateinit var seatsArrayList: ArrayList<SeatsData>
    private lateinit var seatsDb: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverallTrainSeatsBinding.inflate(inflater, container, false)
        seatsRecycleView = binding.displayOverallSeatsRV
        seatsRecycleView.layoutManager = LinearLayoutManager(context)
        seatsArrayList = arrayListOf()

        insertTrainName()

        binding.searchSeatName.setOnClickListener {
            val name = binding.textFieldSearchSeatName.text.toString()

            try {
                if (binding.textFieldSearchSeatName.text.isEmpty()) {
                    Toast.makeText(requireContext(), "No selected train", Toast.LENGTH_SHORT).show()
                } else {
                    getSeatsData(name)
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.clearRecyclerSeatName.setOnClickListener {
            try {
                if (seatsRecycleView.isShown) {
                    seatsArrayList.clear()
                    binding.textFieldSearchSeatName.text.clear()
                    seatsRecycleView.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Nothing shown on screen", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
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
            seatsDb.child(trainName).child("Coach $i")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (seatSnap in snapshot.children) {
                                val seats = seatSnap.getValue(SeatsData::class.java)
                                seatsArrayList.add(seats!!)
                            }
                            seatsRecycleView.adapter =
                                SeatsAdapter(seatsArrayList, this@OverallTrainSeatsFragment)
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

    override fun onItemClick(data: SeatsData) {
        val bundle = bundleOf("coachNumber" to data.coachNum)
        findNavController().navigate(
            R.id.action_overallTrainSeatsFragment_to_btmSheetSeatsFragment,
            bundle
        )
        Toast.makeText(requireContext(), "Clicked on ${data.coachNum}", Toast.LENGTH_SHORT).show()
    }
}