package com.superplay.pomodoroapp.repository

import com.superplay.pomodoroapp.model.dto.PomodoroDTO

interface PomodoroRespository {
    suspend fun save(pomodoroDTO    : PomodoroDTO) : Long
    suspend fun delete(pomodoroDTO  : PomodoroDTO)
    suspend fun update(pomodoroDTO  : PomodoroDTO)
    suspend fun find(id:Long)       : PomodoroDTO
    suspend fun findAll()           : List<PomodoroDTO>
}