package com.example.railwayreservation.passenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class SchedulelistActivity : AppCompatActivity() {

    private lateinit var myRef: DatabaseReference
    private lateinit var scheduleRecyclerView : RecyclerView
    private lateinit var scheduleArrayList : ArrayList<Schedule>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedulelist)

        scheduleRecyclerView = findViewById(R.id.scheduleCard_view)
        scheduleRecyclerView.layoutManager = LinearLayoutManager(this)
        scheduleRecyclerView.setHasFixedSize(true)

        scheduleArrayList = arrayListOf<Schedule>()
        getScheduleData()
    }

    private fun getScheduleData() {

        myRef = FirebaseDatabase.getInstance().getReference("TrainSchedule")

        myRef.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (scheduleSnapshot in snapshot.children) {

                        val schedule = scheduleSnapshot.getValue(Schedule::class.java)
                        scheduleArrayList.add(schedule!!)

                    }

                    scheduleRecyclerView.adapter = ScheduleAdapter(scheduleArrayList)
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}