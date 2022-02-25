package com.example.railwayreservation.admin.register

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentAdminRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AdminRegisterFragment : Fragment() {

    private var _binding: FragmentAdminRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var adminName: EditText
    private lateinit var adminEmail: EditText
    private lateinit var adminPassword: EditText
    private lateinit var adminConfirmPassword: EditText
    private lateinit var adminPhone: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminRegisterBinding.inflate(inflater, container, false)

        adminName = binding.enterNameTxt
        adminEmail = binding.enterEmailTxt
        adminPassword = binding.enterPassTxt
        adminConfirmPassword = binding.enterConfirmPassTxt
        adminPhone = binding.enterPhoneTxt

        firebaseAuth = Firebase.auth

        binding.registerAdminBtn.setOnClickListener {
            checkEmptyInput()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adminRegisterMainTopAppBar.setOnClickListener {
            findNavController().navigate(R.id.action_adminRegisterFragment_to_adminLoginFragment)
        }
    }

    private fun checkEmptyInput() {
        when {
            TextUtils.isEmpty(adminName.text.toString().trim()) -> {
                adminName.error = "Please enter a name"
            }
            TextUtils.isEmpty(adminEmail.text.toString().trim()) -> {
                adminEmail.error = "Please a valid email"
            }
            TextUtils.isEmpty(adminPassword.text.toString().trim()) -> {
                adminPassword.error = "Please enter password"
            }
            TextUtils.isEmpty(adminConfirmPassword.text.toString().trim()) -> {
                adminConfirmPassword.error = "Please reenter password"
            }
            TextUtils.isEmpty(adminPhone.text.toString().trim()) -> {
                adminPhone.error = "Please enter valid phone no"
            }

            adminName.text.toString().isNotEmpty() &&
                    adminEmail.text.toString().isNotEmpty() &&
                    adminPassword.text.toString().isNotEmpty() &&
                    adminConfirmPassword.text.toString().isNotEmpty() &&
                    adminPhone.text.toString().isNotEmpty() -> {
                if (adminEmail.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (adminPassword.text.toString().length >= 6) {
                        if (adminPassword.text.toString() == adminConfirmPassword.text.toString()) {
                            registerToFirebase()
                        } else {
                            binding.enterConfirmPassTextLayout.helperText = "Password not same, please try again"
                        }
                    } else {
                        binding.enterPassTextLayout.helperText = "Please enter at least 6 characters"
                    }
                } else {
                    adminEmail.error = "Enter a valid email address"
                }
            }
        }
    }

    private fun registerToFirebase() {
        firebaseAuth.createUserWithEmailAndPassword(
            adminEmail.text.toString(),
            adminPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Register Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}