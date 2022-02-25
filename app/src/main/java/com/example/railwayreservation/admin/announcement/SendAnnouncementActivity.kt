package com.example.railwayreservation.admin.announcement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.railwayreservation.admin.refund.MakeRefund
import com.example.railwayreservation.databinding.ActivitySendAnnouncementBinding
import com.example.railwayreservation.passenger.PassengerAccount
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "announceTopic"

class SendAnnouncementActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendAnnouncementBinding
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendAnnouncementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRefund.setOnClickListener {
            startActivity(Intent(this, MakeRefund::class.java))

        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding.buttonSendAnnouncement.setOnClickListener {
            val announceTitle = binding.editTextAnnounceTitle.text.toString()
            val announceMessage = binding.editTextAnnonceMsg.text.toString()

            if(announceTitle.isNotEmpty() && announceMessage.isNotEmpty()){
                PushNotification(
                    NotificationData(announceTitle, announceMessage),
                    TOPIC
                ).also {
                    sendAnnouncement(it)
                }
            }
        }
    }

    private fun sendAnnouncement(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)

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