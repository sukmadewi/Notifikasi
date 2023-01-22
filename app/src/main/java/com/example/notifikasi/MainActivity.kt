package com.example.notifikasi

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private lateinit var notifManager: NotificationManager
    private lateinit var notifChannel: NotificationChannel
    private lateinit var notifBuilder: Notification.Builder

    private val ChannelId = "i.apps.notifications"
    private val ChannelDesc = "Test Notifikasi"



    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tombolNotif = findViewById<Button>(R.id.btn1)
        notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        tombolNotif.setOnClickListener{
            val intent = Intent(this,AfterNotif::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            notifChannel = NotificationChannel(ChannelId, ChannelDesc, NotificationManager.IMPORTANCE_HIGH)
            notifChannel.enableLights(true)
            notifChannel.lightColor = Color.GREEN
            notifChannel.enableVibration(false)
            notifManager.createNotificationChannel(notifChannel)


            notifBuilder = Notification.Builder(this, ChannelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentTitle("Notifikasi Berhasil Terkirim")
                .setContentText("Sukma Dewi Wulansari")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)



            notifManager.notify(1234, notifBuilder.build())
        }
    }
}