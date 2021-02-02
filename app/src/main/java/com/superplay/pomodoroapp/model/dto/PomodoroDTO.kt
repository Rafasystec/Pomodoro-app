package com.superplay.pomodoroapp.model.dto

import com.superplay.pomodoroapp.model.EnumPomodoroDay
import com.superplay.pomodoroapp.model.Pomodoro
import com.superplay.pomodoroapp.model.toPomodoroDTO
import java.util.*

class PomodoroDTO(
        var id:Long,
        var initialTime:Long,
        var finalTime:Long,
        var date: Date,
        var isTheFirst:Boolean = false,
        var day:EnumPomodoroDay?= null
)

fun PomodoroDTO.toPomodoro() : Pomodoro{
    return with(this){
        Pomodoro(
            id          = this.id,
            initialTime = this.initialTime,
            finalTime   = this.finalTime,
            date        = this.date
        )
    }
}

