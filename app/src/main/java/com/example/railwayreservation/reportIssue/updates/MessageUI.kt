package com.example.railwayreservation.reportIssue.updates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivityMessageUiBinding
import com.example.railwayreservation.passenger.PassengerHome
import com.example.railwayreservation.reportIssue.IssuesData
import com.google.firebase.database.*

class MessageUI : AppCompatActivity() {

    private lateinit var binding: ActivityMessageUiBinding
    private lateinit var messageDb: DatabaseReference
    private lateinit var messageArrayList: ArrayList<IssuesData>
    private lateinit var messageRecyclerView: RecyclerView
    val msgTag = "MessageUI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageUiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.messagesUITopAppBar.setOnClickListener {
            startActivity(Intent(this, PassengerHome::class.java))
        }

        try {
            messageRecyclerView = binding.messagesRV
            messageRecyclerView.layoutManager = LinearLayoutManager(baseContext)
            messageRecyclerView.setHasFixedSize(true)
            messageArrayList = arrayListOf()

            getMessages()
        }catch (e: Exception) {
            Log.d(msgTag, "${e.message}")
        }
    }

    private fun getMessages() {
        messageDb = FirebaseDatabase.getInstance().getReference("Messages")
        messageDb.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (messages in snapshot.children) {
                        val messagesList = messages.getValue(IssuesData::class.java)
                        messageArrayList.add(messagesList!!)
                    }
                    messageRecyclerView.adapter = MessageUpdatesAdapter(messageArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}