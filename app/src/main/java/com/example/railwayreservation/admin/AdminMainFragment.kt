package com.example.railwayreservation.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.announcement.SendAnnouncementActivity
import com.example.railwayreservation.databinding.FragmentAdminMainBinding
import com.example.railwayreservation.reportIssue.IssueManage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminMainFragment : Fragment() {

    private lateinit var _binding: FragmentAdminMainBinding
    private val binding get() = _binding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.topAppBar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.signOut -> {
                        Firebase.auth.signOut()
                        navController.navigate(R.id.action_adminMainFragment_to_adminLoginFragment)
                        Toast.makeText(context, "Sign out successfully", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
        }

        binding.trainAnnounceBtn.setOnClickListener {
            val intent = Intent(context, SendAnnouncementActivity::class.java).apply {

            }
            startActivity(intent)
        }

        binding.trainManageBtn.setOnClickListener {
            navController.navigate(R.id.action_adminMainFragment_to_trainManageFragment)
        }

        binding.trainIssuesBtn.setOnClickListener {
            val intent = Intent(context, IssueManage::class.java).apply {

            }
            startActivity(intent)
        }
    }
}