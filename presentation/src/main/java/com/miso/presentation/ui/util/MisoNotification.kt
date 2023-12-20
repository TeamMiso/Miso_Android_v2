package com.miso.presentation.ui.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.miso.design_system.R
import com.miso.presentation.ui.login.LoginActivity

class MisoNotification : FirebaseMessagingService() {
    companion object {
        private const val CHANNEL_NAME = "MISO"
        private const val CHANNEL_DESCRIPTION = "MISO 알림"
        private const val CHANNEL_ID = "miso_channel_id"
        private const val GROUP_NAME = "com.miso.miso_android_v2"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotificationChannel()
        message.notification?.let { sendNotification(it.title, it.body) }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        saveDeviceToken(token)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply { description = CHANNEL_DESCRIPTION }

        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
    }

    private fun sendNotification(title: String?, body: String?) {
        val messageId = System.currentTimeMillis().toInt()
        val intent = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, messageId, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_miso)
            setContentTitle(title)
            setContentText(body)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setVibrate(longArrayOf(1000, 1000, 1000))
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            setGroup(GROUP_NAME)
            setGroupSummary(true)
            setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager?.notify(messageId, notificationBuilder.build())
    }

    private fun saveDeviceToken(token: String) {
        val deviceTokenSF = getSharedPreferences("deviceToken", MODE_PRIVATE)
        deviceTokenSF.edit().putString("device", token).apply()
    }
}