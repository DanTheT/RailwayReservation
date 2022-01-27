package com.example.railwayreservation.admin.trainSeats.updateSeats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentUpdateSeatsBinding
import com.google.firebase.database.DatabaseReference


class UpdateSeatsFragment : Fragment() {

    private var _binding: FragmentUpdateSeatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var seatsDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_update_seats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}