package com.superplay.pomodoroapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.superplay.pomodoroapp.dao.PomodoroDao
import com.superplay.pomodoroapp.model.Pomodoro
import com.superplay.pomodoroapp.util.DateTimeConverter

@Database(
        version = 1,
        entities = [
            Pomodoro::class
        ]
)
@TypeConverters(DateTimeConverter::class)
abstract class PomodoroDB : RoomDatabase() {
    abstract fun pomodoroDAO():PomodoroDao
}