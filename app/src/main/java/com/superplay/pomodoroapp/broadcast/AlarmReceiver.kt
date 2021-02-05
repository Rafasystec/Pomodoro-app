package com.superplay.pomodoroapp.broadcast

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import com.superplay.pomodoroapp.util.INITIAL_TIME
import com.superplay.pomodoroapp.util.NotificationUtil



class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "pomodoro:alarme")
        wl.acquire(25*60*1000L /*26 minutes*/)
        NotificationUtil.notification(context)
        wl.release()
    }

    fun setAlarm(context: Context) {
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, AlarmReceiver::class.java)
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)
        val systemTime = System.currentTimeMillis()
//        manager.set(AlarmManager.RTC_WAKEUP,  systemTime + INITIAL_TIME,pi)
        manager.set(AlarmManager.RTC_WAKEUP,  systemTime + 25*60*1000,pi)
    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)
    }

}