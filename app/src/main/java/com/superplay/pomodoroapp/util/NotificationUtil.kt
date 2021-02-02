package com.superplay.pomodoroapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.superplay.pomodoroapp.R

object NotificationUtil {

    val CHANNEL_ID = "default"

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context){


        val manager             = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName         = context.getString(R.string.notification_channel)
        val channelDescription  = context.getString(R.string.channel_description)
        val channel             = NotificationChannel(CHANNEL_ID
                                                    ,channelName
                                                    ,NotificationManager.IMPORTANCE_HIGH).apply {
                                    description = channelDescription
                                    enableLights(true)
                                    lightColor = Color.RED
                                    enableVibration(true)
                                }
        channel.setSound(getSound(context), buildSoundAttributes())
        manager.createNotificationChannel(channel)
    }

    fun notification(context: Context){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(ActivityCompat.getColor(context,R.color.red))
            .setAutoCancel(false)
            .setSound(getSound(context))
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1,notificationBuilder.build())
    }

    fun getSound(context: Context) = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.pomodoro)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun buildSoundAttributes() : AudioAttributes{
        return AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }
}