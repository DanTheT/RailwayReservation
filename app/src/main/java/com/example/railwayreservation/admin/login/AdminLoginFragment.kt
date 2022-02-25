package com.example.railwayreservation.admin.login

import android.content.Intent
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
import com.example.railwayreservation.MainActivity
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

        binding.adminLoginAppBar.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java).apply {

            }
            startActivity(intent)
        }

        if (isCurrentUser != null) {
            Toast.makeText(
                context,
                "Welcome Back",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigate(R.id.action_adminLoginFragment_to_adminMainFragment)
        }

        emailFocus()
        passFocus()

        binding.signInBtn.setOnClickListener {
            try {
                //signInFromFirebase()
                when {
                    adminEmail.text.toString().isNotEmpty() &&
                            adminPassword.text.toString().isNotEmpty() -> {

                        if (adminEmail.text.toString()
                                .matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                        ) {
                            if (adminPassword.text.toString().length >= 6) {
                                signInFromFirebase()
                                findNavController().navigate(R.id.action_adminLoginFragment_to_adminMainFragment)
                            } else {
                                binding.passwordTxtLayout.helperText =
                                    getString(R.string.password_length)
                            }
                        } else {
                            binding.emailTxtLayout.helperText = getString(R.string.valid_email)
                        }
                    }

                    adminEmail.text.toString().isEmpty() -> {
                        binding.emailTxtLayout.helperText = getString(R.string.email_empty)
                    }

                    adminPassword.text.toString().isEmpty() -> {
                        binding.passwordTxtLayout.helperText = getString(R.string.password_empty)
                    }
                    else -> {
                        binding.emailTxtLayout.helperText = getString(R.string.email_empty)

                        binding.passwordTxtLayout.helperText = getString(R.string.password_empty)
                    }
                }
            } catch (e: Exception) {
                e.message
            }
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_adminLoginFragment_to_adminRegisterFragment)
        }
    }

    private fun emailFocus() {
        binding.inputEmailTxt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                checkEmptyInput()
            }
        }
    }

    private fun passFocus() {
        binding.inputPasswordTxt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                checkInputPass()
            }
        }
    }

    private fun checkEmptyInput() {
        when {
            TextUtils.isEmpty(adminEmail.text.toString().trim()) -> {
                binding.emailTxtLayout.helperText = getString(R.string.email_empty)
            }

            binding.inputEmailTxt.text?.isNotEmpty() == true -> {
                binding.emailTxtLayout.helperText = null
            }
        }
    }

    private fun checkInputPass() {
        when {
            TextUtils.isEmpty(adminPassword.text.toString().trim()) -> {
                binding.passwordTxtLayout.helperText = getString(R.string.password_empty)
            }

            binding.inputPasswordTxt.text?.isNotEmpty() == true -> {
                binding.passwordTxtLayout.helperText = null
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