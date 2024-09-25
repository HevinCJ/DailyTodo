package com.example.dailytodo.firebase

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.dailytodo.R
import com.example.dailytodo.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService:FirebaseMessagingService() {

    companion object{
        private const val CHANNEL_ID = "dailytodo"
        private const val CHANNEL_NAME = "com.example.dailytodo"
        private const val NOTIFICATION_ID = 102
    }

    override fun onMessageReceived(message: RemoteMessage) {

        Log.d("FCM","message received${message.notification?.body}")

        if (message.notification!=null){
            getFirebaseMessage(message.notification!!.title!!,message.notification!!.body!!)
        }
    }



    private fun getFirebaseMessage(title: String, message: String) {


        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )


        var notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationBuilder = notificationBuilder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build(),)

    }

    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteview = RemoteViews("com.example.dailytodo",R.layout.custom_notification_layout)
        remoteview.setTextViewText(R.id.txtviewnottitle,title)
        remoteview.setTextViewText(R.id.txtviewnotcontent,message)
        remoteview.setImageViewResource(R.id.imageView,R.drawable.notification)

       return remoteview
    }

    override fun onNewToken(token: String) {

        Log.d("TOKENFCM", "New token: $token")
        super.onNewToken(token)
    }


}