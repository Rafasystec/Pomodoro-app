package com.superplay.pomodoroapp.util

import com.superplay.pomodoroapp.model.EnumDatePattern
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class TestPomodoroUtil {

    @Test
    fun shoudGroupPomodoroByDate(){
        val today = Date(1612148400000) // 01/02/2021
        val list = listOf(
                PomodoroDTO(2,165465165165,651651651, Date(1612062000000)), // 31/01/2021
                PomodoroDTO(3,165465165165,651651651, Date(1612062000000)),//31/01/2021
                PomodoroDTO(4,165465165165,651651651, Date(1612148400000)),//01/02/2021
                PomodoroDTO(5,165465165165,651651651, Date(1612004400000)),//30/01/2021
                PomodoroDTO(6,165465165165,651651651, Date(1612004400000)))//30/01/2021

        val grouping = PomodoroUtil.groupPomodoroByDate(list,today)

        assertEquals(1, grouping.listToday.size)
        assertEquals(2, grouping.listYesterday.size)
        assertEquals(2, grouping.listOld.size)
    }

    @Test
    fun orderTheGroupPomodoro(){
        val today = Date(1612148400000) // 01/02/2021
        val list = listOf(
                PomodoroDTO(2,165465165165,651651651, Date(1612062000000)),//31/01/2021
                PomodoroDTO(3,165465165165,651651651, Date(1612062000000)),//31/01/2021
                PomodoroDTO(4,165465165165,651651651, Date(1612148400000)),//01/02/2021
                PomodoroDTO(5,165465165165,651651651, Date(1612004400000)),//30/01/2021
                PomodoroDTO(6,165465165165,651651651, Date(1612004400000)),//30/01/2021
                PomodoroDTO(6,165465165165,651651651, Date(1611918000000)))//29/01/2021

        val finalList = PomodoroUtil.groupAndOrderAPomodorList(list,today)
        val firstOne  = finalList.first()
        val lastLast  = finalList.last()
        assertEquals("01/02/2021",DateTimeUtil.formatADate(firstOne.date,EnumDatePattern.DD_MM_YYYY))
        assertEquals("29/01/2021",DateTimeUtil.formatADate(lastLast.date,EnumDatePattern.DD_MM_YYYY))
    }
}