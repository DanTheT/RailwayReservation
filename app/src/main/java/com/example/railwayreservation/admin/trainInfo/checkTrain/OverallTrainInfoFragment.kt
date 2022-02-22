package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.ParcelizeInfo
import com.example.railwayreservation.admin.trainInfo.data.TrainInfo
import com.example.railwayreservation.admin.trainInfo.data.TrainInfoAdapter
import com.example.railwayreservation.databinding.FragmentOverallTrainInfoBinding
import com.google.firebase.database.*

class OverallTrainInfoFragment : Fragment(), TrainInfoAdapter.OnItemClick {

    private var _binding: FragmentOverallTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainInfoRecyclerView: RecyclerView
    private lateinit var trainArrayList: ArrayList<TrainInfo>
    private lateinit var trainInfoDatabase: DatabaseReference
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverallTrainInfoBinding.inflate(inflater, container, false)

        trainInfoRecyclerView = binding.displayTrainInfo
        trainInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        trainInfoRecyclerView.setHasFixedSize(true)

        trainArrayList = arrayListOf()
        retrieveTrainInfo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.overallTrainInfoMainTopAppBar.setNavigationOnClickListener {
            navController.navigate(R.id.action_overallTrainInfoFragment_to_trainManageFragment)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_overallTrainInfoFragment_to_addNewInfoFragment)
        }
    }

    private fun retrieveTrainInfo() {
        trainInfoDatabase = FirebaseDatabase.getInstance().getReference("SpecificTrainInfo")
        trainInfoDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (trainInfo in snapshot.children) {
                        val info = trainInfo.getValue(TrainInfo::class.java)
                        trainArrayList.add(info!!)
                    }
                    trainInfoRecyclerView.adapter = TrainInfoAdapter(trainArrayList, this@OverallTrainInfoFragment)
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

    @SuppressLint("InflateParams")
    override fun onItemClick(data: TrainInfo) {
        val name: String = data.trainName
        val status: String = data.status
        val info = ParcelizeInfo (
            name, status
                )

        val action = OverallTrainInfoFragmentDirections.actionOverallTrainInfoFragmentToBottomSheetFragment(info)
        findNavController().navigate(action)
    }
}