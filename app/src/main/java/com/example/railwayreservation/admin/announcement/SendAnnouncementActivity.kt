package com.example.railwayreservation.admin.announcement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.railwayreservation.R
import com.example.railwayreservation.databinding.ActivitySendAnnouncementBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/announceTopic"

class SendAnnouncementActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendAnnouncementBinding
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendAnnouncementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding.buttonSendAnnouncement.setOnClickListener {
            val announceTitle = binding.editTextAnnounceTitle.text.toString()
            val announceMessage = binding.editTextAnnonceMsg.text.toString()

            if(announceTitle.isNotEmpty() && announceMessage.isNotEmpty()){
                PushAnnouncement(
                    AnnouncementData(announceTitle, announceMessage),
                    TOPIC
                ).also {
                    sendAnnouncement(it)
                }
            }
        }
    }

    private fun sendAnnouncement(announcement: PushAnnouncement) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postAnnouncement(announcement)

            if(response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }else{
                Log.e(TAG, response.errorBody().toString())
            }
        }catch (e: Exception){
            Log.e(TAG, e.toString())
        }
    }
}