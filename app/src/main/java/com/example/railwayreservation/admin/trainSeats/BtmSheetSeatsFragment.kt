package com.example.railwayreservation.admin.trainSeats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.railwayreservation.admin.trainSeats.data.ParcelizedSeat
import com.example.railwayreservation.databinding.FragmentBtmSheetSeatsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtmSheetSeatsFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBtmSheetSeatsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<BtmSheetSeatsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtmSheetSeatsBinding.inflate(inflater, container, false)

        binding.btmSheetCoachNumber.text = args.coachNumber.coachNum

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coachNo = binding.btmSheetCoachNumber.text.toString()

        val seatParcel = ParcelizedSeat(
            coachNo
        )
        val action = BtmSheetSeatsFragmentDirections.actionBtmSheetSeatsFragmentToUpdateSeatsFragment(seatParcel)

        binding.updateTrainSeat.setOnClickListener {
            findNavController().navigate(action)
        }
    }
}