package com.example.railwayreservation.passenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentTrainInfoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PassengerReservation : AppCompatActivity() {
//    , View.OnClickListener

//    private lateinit var btnCallFrag: Button

    //    private var _binding: FragmentTrainInfoBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var trainInfoDb: DatabaseReference
//    private lateinit var navController: NavController
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_reservation)
//
//
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View? {
//            _binding = FragmentTrainInfoBinding.inflate(inflater, container, false)
//
//            preloadTrainType()
//
//            binding.searchButton.setOnClickListener {
//                val selectType = binding.trainTypeSpinner.selectedItem.toString()
//                selectType.checkTrainLine()
//            }
//
//            return binding.root
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//            navController = Navigation.findNavController(view)
//            view.findViewById<Button>(R.id.selection_btn).setOnClickListener(this)
//        }
//
//        private fun checkTrainLine(trainType: String) {
//            trainInfoDb = FirebaseDatabase.getInstance().getReference("TrainInfo")
//            trainInfoDb.child(trainType).get().addOnSuccessListener {
//                if (it.exists()) {
//                    val endStation = it.child("endStation").value
//                    val startStation = it.child("startStation").value
//                    val trainLine = it.child("trainLine").value
//                    val trainNum = it.child("trainNum").value
//
//                    binding.trainStartTextview.text = startStation.toString()
//                    binding.trainEndTextview.text = endStation.toString()
//                    binding.trainLineTextview.text = trainLine.toString()
//                    binding.trainNumTextview.text = trainNum.toString()
//                }
//            }
//
//        }
//
//        fun preloadTrainType() {
//            val spinner: Spinner = binding.trainTypeSpinner
//            ArrayAdapter.createFromResource(
//                requireContext(),
//                R.array.trainType_items,
//                android.R.layout.simple_spinner_item
//            ).also { adapter ->
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                spinner.adapter = adapter
//            }
//        }
//
//
//        override fun onClick(v: View?) {
//            val trainTypeBtn = binding.trainTypeSpinner.selectedItem
//            when (v!!.id) {
//                R.id.selection_btn -> {
//                    if (trainTypeBtn != null) {
//                        val bundle = bundleOf("recipient" to trainTypeBtn.toString())
//                        navController.navigate(
//                            R.id.action_trainInfoFragment_to_trainScheduleFragment,
//                            bundle
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

//        btnCallFrag = findViewById(R.id.btnCallFrag)
//        btnCallFrag.setOnClickListener(this)

//        val trainInfo = TrainInfoFragment()
//        supportFragmentManager.beginTransaction().add(R.id.container, TrainInfoFragment()).commit()


//    override fun onClick(view: View) {
//        if (view.getId() === R.id.btnCallFrag) {
////            supportFragmentManager.beginTransaction().replace(R.id.container, TrainInfoFragment())
////                .commit()
//            val trainInfo = TrainInfoFragment()
//        supportFragmentManager.beginTransaction().add(R.id.container, TrainInfoFragment()).commit()
//            btnCallFrag.setVisibility(View.GONE)
//
//        }
//    }

    }
}