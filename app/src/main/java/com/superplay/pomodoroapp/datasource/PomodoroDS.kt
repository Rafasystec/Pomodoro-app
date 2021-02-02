package com.superplay.pomodoroapp.datasource

import com.superplay.pomodoroapp.dao.PomodoroDao
import com.superplay.pomodoroapp.model.EnumPomodoroDay
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import com.superplay.pomodoroapp.model.dto.toPomodoro
import com.superplay.pomodoroapp.model.toPomodoroDTO
import com.superplay.pomodoroapp.repository.PomodoroRespository
import com.superplay.pomodoroapp.util.PomodoroUtil
import java.util.*

class PomodoroDS(private val pomodoroDao: PomodoroDao) : PomodoroRespository {
    override suspend fun save(pomodoroDTO: PomodoroDTO) : Long{
        return pomodoroDao.insert(pomodoroDTO.toPomodoro())
    }

    override suspend fun update(pomodoroDTO: PomodoroDTO) {
        pomodoroDao.update(pomodoroDTO.toPomodoro())
    }

    override suspend fun delete(pomodoroDTO: PomodoroDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun find(id: Long): PomodoroDTO {
        return pomodoroDao.getPomodoro(id).toPomodoroDTO()
    }

    override suspend fun findAll(): List<PomodoroDTO> {
        val result = pomodoroDao.findAll()
        return PomodoroUtil.getListPomodoroDTOFromPomodorList(result)
    }

    private fun mockList() = listOf(
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(2,1_500_000L,1_200_000, Date(1612062000000)),
            PomodoroDTO(3,1_500_000L,1_140_000, Date(1612062000000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(4,1_500_000L,480_000,   Date(1612148400000)),
            PomodoroDTO(5,1_500_000L,600_000,   Date(1612004400000)),
            PomodoroDTO(5,1_500_000L,600_000,   Date(1612004400000)),
            PomodoroDTO(5,1_500_000L,600_000,   Date(1612004400000)),
            PomodoroDTO(6,1_500_000L,0,         Date(1611918000000)))
}