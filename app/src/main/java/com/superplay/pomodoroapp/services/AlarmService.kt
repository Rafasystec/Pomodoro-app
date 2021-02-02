package com.superplay.pomodoroapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.superplay.pomodoroapp.broadcast.AlarmReceiver

class AlarmService : Service() {

    val alarm: AlarmReceiver by lazy {
        AlarmReceiver()
    }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        alarm.setAlarm(this)
        return START_STICKY
    }

    override fun onStart(intent: Intent?, startId: Int) {
        alarm.setAlarm(this)
    }
    override fun onBind(intent: Intent): IBinder {
        return null!!
    }

    override fun stopService(name: Intent?): Boolean {
        alarm.cancelAlarm(applicationContext)
        return super.stopService(name)

    }

    override fun onDestroy() {
        alarm.cancelAlarm(applicationContext)
        super.onDestroy()
    }

}