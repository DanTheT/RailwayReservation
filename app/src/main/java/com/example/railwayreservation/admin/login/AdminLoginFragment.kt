package com.example.railwayreservation.admin.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.FragmentAdminLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminLoginFragment : Fragment() {
    private var _binding: FragmentAdminLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var navController: NavController

    private lateinit var adminEmail: TextInputEditText
    private lateinit var emailLayout: TextInputLayout
    private lateinit var adminPassword: TextInputEditText
    private lateinit var passwordLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminLoginBinding.inflate(inflater, container, false)

        emailLayout = binding.emailTxtLayout
        adminEmail = binding.inputEmailTxt
        adminPassword = binding.inputPasswordTxt
        passwordLayout = binding.passwordTxtLayout

        firebaseAuth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isCurrentUser = firebaseAuth.currentUser

        navController = Navigation.findNavController(view)

        if (isCurrentUser != null) {
            Toast.makeText(
                context,
                "Welcome Back",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigate(R.id.action_adminLoginFragment_to_adminMainFragment)
        }

        binding.signInBtn.setOnClickListener {
            try {
                signInFromFirebase()
                findNavController().navigate(R.id.action_adminLoginFragment_to_adminMainFragment)
            } catch (e: Exception) {
                e.message
            }
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_adminLoginFragment_to_adminRegisterFragment)
        }
    }

    private fun checkEmptyInput() {
        when {
            TextUtils.isEmpty(adminEmail.text.toString().trim()) -> {
                adminEmail.error = getString(R.string.email_empty)
            }
            TextUtils.isEmpty(adminPassword.text.toString().trim()) -> {
                adminPassword.error = getString(R.string.password_empty)
            }

            adminEmail.text.toString().isNotEmpty() &&
                    adminPassword.text.toString().isNotEmpty() -> {

                if (adminEmail.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (adminPassword.text.toString().length >= 6) {
                        signInFromFirebase()
                    } else {
                        adminPassword.error = getString(R.string.password_length)
                    }
                } else {
                    adminEmail.error = getString(R.string.valid_email)
                }
            }
        }
    }

    private fun signInFromFirebase() {
        firebaseAuth.signInWithEmailAndPassword(
            adminEmail.text.toString(),
            adminPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    context,
                    "Login successfully as ${adminEmail.text}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}