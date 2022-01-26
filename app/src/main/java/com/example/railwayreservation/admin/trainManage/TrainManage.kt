package com.example.railwayreservation.admin.trainManage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.railwayreservation.R
import com.example.railwayreservation.admin.NavigationFrag
import com.example.railwayreservation.databinding.ActivityTrainManageBinding

class TrainManage : AppCompatActivity(), NavigationFrag {

    private lateinit var binding: ActivityTrainManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainManageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction()
            .add(R.id.train_manage_container, TrainManageFragment())
            .commit()
    }

    override fun navFrag(fragment: Fragment, addToStack: Boolean) {
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.train_manage_container, fragment)

        if(addToStack){
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.commit()
    }
}