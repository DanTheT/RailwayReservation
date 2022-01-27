package com.example.railwayreservation.admin.announcement

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.railwayreservation.MainActivity
import com.example.railwayreservation.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

private const val CHANNEL_ID = "announcement_channel"

class ServiceFirebase: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this, SendAnnouncementActivity::class.java)
        val announcementManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val announcementID = Random.nextInt()

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0,intent, FLAG_ONE_SHOT)
        val announcement = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_train_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        announcementManager.notify(announcementID, announcement)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createAnnouncementChannel(announcementManager)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createAnnouncementChannel(notificationManager: NotificationManager){
        val channelName = "announcementChannel"

        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "The announcement description"
            enableLights(true)
            lightColor = Color.BLUE
        }
        notificationManager.createNotificationChannel(channel)
    }
}