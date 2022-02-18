package com.example.railwayreservation.admin.trainInfo.checkTrain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.admin.trainInfo.data.ParcelizeInfo
import com.example.railwayreservation.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<BottomSheetFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        binding.btmSheetTrainName.text = args.nameAndNumber.trainName
        binding.btmSheetTrainStatus.text = args.nameAndNumber.status

        binding.updateTrainInfoBtn.setOnClickListener {
            val trainName = binding.btmSheetTrainName.text.toString()
            val status = binding.btmSheetTrainStatus.text.toString()

            val info = ParcelizeInfo(
                trainName, status
            )

            val action = BottomSheetFragmentDirections.actionBottomSheetFragmentToUpdateTrainInfoFragment(info)
            findNavController().navigate(action)
        }

        return binding.root
    }
}