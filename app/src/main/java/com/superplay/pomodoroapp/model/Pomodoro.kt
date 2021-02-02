package com.superplay.pomodoroapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import com.superplay.pomodoroapp.util.DateTimeConverter
import java.util.*

@Entity(tableName = "pomodoro")
data class Pomodoro(
        @PrimaryKey(autoGenerate = true)
        val id:Long,
        val initialTime:Long,
        val finalTime:Long,
        val date: Date

)

fun Pomodoro.toPomodoroDTO() : PomodoroDTO{
        return with(this){
                PomodoroDTO(
                        id              =   this.id,
                        initialTime     =   this.initialTime,
                        finalTime       =   this.finalTime,
                        date            =   this.date
                )
        }
}