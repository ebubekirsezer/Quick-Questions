package com.ess.quickquestions.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.ess.quickquestions.MainActivity
import com.ess.quickquestions.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import kotlin.random.Random

class FirebaseNotificationService : FirebaseMessagingService() {

    companion object{
        const val PARAM_CUSTOM_MESSAGE = "PARAM_CUSTOM_MESSAGE"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var customMessage: String? = null
        if (remoteMessage.data.isNotEmpty()) {
            println("Message data payload: " + remoteMessage.data)
            customMessage = remoteMessage.data["customMessage"]
        }

        remoteMessage.notification?.let {
            println("Message notification body: " + it.body)
            sendNotification(it.title, it.body, customMessage)
        }
    }

    override fun onNewToken(token: String) {

    }

    private fun sendNotification(title: String?, body: String?, customMessage: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtras(Bundle().apply {
                putString(PARAM_CUSTOM_MESSAGE, customMessage)
            })
        }

        val pendingIntent = PendingIntent.getActivity(
            this, java.util.Random().nextInt(), intent, PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.qqlogo)
            .setContentTitle(title)
            .setContentText(body)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "Ebubekir Sezer",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.notify(0,notificationBuilder.build())
        }
    }
}