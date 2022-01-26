package com.example.railwayreservation.admin.trainInfo.addTrain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentAddTrainInfoBinding
import com.google.firebase.database.DatabaseReference

class AddTrainInfoFragment : Fragment() {

    private var _binding: FragmentAddTrainInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var trainDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTrainInfoBinding.inflate(inflater, container, false)

        insertTrainCoach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertTrainStartStation()
        insertTrainEndStation()
    }

    private fun insertTrainCoach(){
        val spinner: Spinner = binding.addCoachNumSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.numOfTrainCar,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun insertTrainStartStation() {
        val startSpinner: Spinner = binding.addTrainInfoStartSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.station_names,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            startSpinner.adapter = adapter
        }
    }

    private fun insertTrainEndStation() {
        val endSpinner: Spinner = binding.addTrainInfoEndSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.station_names,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            endSpinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}