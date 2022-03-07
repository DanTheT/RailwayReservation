package com.example.railwayreservation.reportIssue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityHelperInfoBinding
import com.example.railwayreservation.reportIssue.tabsUI.HelperViewPagerAdapter
import com.example.railwayreservation.reportIssue.tabsUI.ScheduleHelperInfoFragment
import com.example.railwayreservation.reportIssue.tabsUI.StationHelperInfoFragment
import com.example.railwayreservation.reportIssue.tabsUI.TrainHelperInfoFragment

class HelperInfo : AppCompatActivity() {

    private lateinit var binding: ActivityHelperInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelperInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        insertTabs()

        binding.infoHelperTopAppBar.setOnClickListener {
            startActivity(Intent(this, ReportIssue::class.java))
        }
    }

    private fun insertTabs() {
        val  adapter = HelperViewPagerAdapter(supportFragmentManager)

        adapter.addFragments(TrainHelperInfoFragment(), "Train")
        adapter.addFragments(ScheduleHelperInfoFragment(), "Schedule")
        adapter.addFragments(StationHelperInfoFragment(), "Station")
        binding.helperViewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.helperViewPager)
    }
}