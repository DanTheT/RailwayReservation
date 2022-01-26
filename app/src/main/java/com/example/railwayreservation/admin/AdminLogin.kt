package com.example.railwayreservation.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.login.AdminLoginFragment
import com.example.railwayreservation.databinding.ActivityAdminLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminLogin : AppCompatActivity(), NavigationFrag {

    private lateinit var binding: ActivityAdminLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = Firebase.auth

        val isCurrentUser = firebaseAuth.currentUser

        if(isCurrentUser != null){
            supportFragmentManager.beginTransaction()
                .add(R.id.adminLoginContainer, AdminMainFragment())
                .commit()
        }else{
            supportFragmentManager.beginTransaction()
                .add(R.id.adminLoginContainer, AdminLoginFragment())
                .commit()
        }
    }

    override fun navFrag(fragment: Fragment, addToStack: Boolean) {
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.adminLoginContainer, fragment)

        if(addToStack){
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.commit()
    }
}
