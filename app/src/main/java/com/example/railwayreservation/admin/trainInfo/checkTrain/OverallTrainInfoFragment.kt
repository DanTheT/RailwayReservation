package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.trainInfo.data.BriefInfoData
import com.example.railwayreservation.databinding.FragmentOverallTrainInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*

class OverallTrainInfoFragment : Fragment(), TrainInfoAdapter.OnItemClick {

    private var _binding: FragmentOverallTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainInfoRecyclerView: RecyclerView
    private lateinit var trainArrayList: ArrayList<BriefInfoData>
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

        trainArrayList = arrayListOf<BriefInfoData>()
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
                        val info = trainInfo.getValue(BriefInfoData::class.java)
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
    override fun onItemClick(data: BriefInfoData) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.fragment_btm_sheet, null)
        val name = view.findViewById<TextView>(R.id.btmSheetTrainName)
        val coaches = view.findViewById<TextView>(R.id.btmSheetCoaches)
        val trainNumber = view.findViewById<TextView>(R.id.btmSheetTrainNumber)
        val trainStatus = view.findViewById<TextView>(R.id.btmSheetTrainStatus)

        name.text = data.trainName
        coaches.text = data.car
        trainNumber.text = data.trainNum
        trainStatus.text = data.status

        dialog.setContentView(view)
        dialog.show()
    }
}