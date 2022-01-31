package com.example.railwayreservation.admin.trainInfo.addTrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityTrainInfoChangesBinding

class TrainInfoChangesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainInfoChangesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainInfoChangesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        insertTrainStartStation()
        insertTrainEndStation()
    }

    private fun insertTrainStartStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(this, R.layout.list_for_dropdown, lists)
        binding.textFieldStartStation.setAdapter(listsAdapter)
    }

    private fun insertTrainEndStation() {
        val lists = resources.getStringArray(R.array.station_names)

        val listsAdapter = ArrayAdapter(this, R.layout.list_for_dropdown, lists)
        binding.textFieldEndStation.setAdapter(listsAdapter)
    }
}