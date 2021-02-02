package com.superplay.pomodoroapp.model

import com.superplay.pomodoroapp.model.dto.PomodoroDTO

data class PomodoroGroup(
        val listToday       :List<PomodoroDTO>,
        val listYesterday   :List<PomodoroDTO>,
        val listOld         :List<PomodoroDTO>
)