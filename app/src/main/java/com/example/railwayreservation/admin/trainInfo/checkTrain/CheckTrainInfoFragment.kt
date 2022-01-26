package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentCheckTrainInfoBinding
import com.google.firebase.database.*

class CheckTrainInfoFragment : Fragment() {

    private var _binding: FragmentCheckTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainInfoRecyclerView: RecyclerView
    private lateinit var trainArrayList: ArrayList<TrainInfoRecycle>
    private lateinit var trainInfoDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckTrainInfoBinding.inflate(inflater, container, false)

        trainInfoRecyclerView = binding.displayInfoRecycleView
        trainInfoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        trainArrayList = arrayListOf<TrainInfoRecycle>()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveTrainInfo()
    }

    private fun retrieveTrainInfo(){
        trainInfoDatabase = FirebaseDatabase.getInstance().getReference("TrainInfo")
        trainInfoDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(trainInfo in snapshot.children){
                        val info = trainInfo.getValue(TrainInfoRecycle::class.java)
                        trainArrayList.add(info!!)
                    }
                    trainInfoRecyclerView.adapter = CheckTrainInfoAdapter(trainArrayList)
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