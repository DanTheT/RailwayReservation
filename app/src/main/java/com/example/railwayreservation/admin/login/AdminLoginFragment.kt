package com.example.railwayreservation.admin.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.AdminMainFragment
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.admin.register.AdminRegisterFragment
import com.example.railwayreservation.databinding.FragmentAdminLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminLoginFragment : Fragment() {

    private var _binding: FragmentAdminLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var adminEmail: EditText
    private lateinit var adminPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminLoginBinding.inflate(inflater, container, false)

        adminEmail = binding.inputEmailTxt
        adminPassword = binding.inputPassTxt
        firebaseAuth = Firebase.auth
        checkEmptyInput()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBtn.setOnClickListener {
            val registerNavigation = activity as NavigationFrag
            registerNavigation.navFrag(AdminRegisterFragment(), false)
        }

        binding.signInBtn.setOnClickListener {
            signInFromFirebase()
            Toast.makeText(context, "Sign in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmptyInput() {
        when{
            TextUtils.isEmpty(adminEmail.text.toString().trim()) -> {
                adminEmail.error = "Please a valid email"
            }
            TextUtils.isEmpty(adminPassword.text.toString().trim()) -> {
                adminPassword.error = "Please enter password"
            }

            adminEmail.text.toString().isNotEmpty() &&
                    adminPassword.text.toString().isNotEmpty() -> {

                if(adminEmail.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(adminPassword.text.toString().length >= 6){
                        signInFromFirebase()
                        Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        adminPassword.error = "Please enter at least 6 characters"
                    }
                }else{
                    adminEmail.error = "Enter a valid email address"
                }
            }
        }
    }

    private fun signInFromFirebase() {
        firebaseAuth.signInWithEmailAndPassword(adminEmail.text.toString(), adminPassword.text.toString()).addOnCompleteListener {
                task -> if(task.isSuccessful){
            val toMainPage = activity as NavigationFrag
            toMainPage.navFrag(AdminMainFragment(), addToStack = true)
        }else{
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
        }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}