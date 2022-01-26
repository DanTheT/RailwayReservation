package com.example.railwayreservation.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.login.AdminLoginFragment
import com.example.railwayreservation.databinding.FragmentAdminMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainFragment : Fragment() {

    private lateinit var _binding: FragmentAdminMainBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminMainBinding.inflate(inflater, container, false)

        binding.trainSignOutBtn.setOnClickListener {
            Firebase.auth.signOut()
            val loginNavigation = activity as NavigationFrag
            loginNavigation.navFrag(AdminLoginFragment(), addToStack = false)
            Toast.makeText(context, "Sign Out", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trainManageBtn.setOnClickListener {
            /*val intent = Intent(context, TrainManagePageActivity::class.java).apply {

            }
            startActivity(intent)*/
        }

        binding.trainIssuesBtn.setOnClickListener {
            /*val intent = Intent(context, ReportIssueManageActivity::class.java).apply {

            }
            startActivity(intent)*/
        }
    }
}