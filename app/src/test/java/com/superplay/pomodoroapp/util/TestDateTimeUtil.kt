package com.superplay.pomodoroapp.util

import android.text.format.DateUtils
import com.superplay.pomodoroapp.model.EnumDatePattern
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class TestDateTimeUtil {

    @Test
    fun shouldCalculateSndShowTimeSixSeconds(){
        val sixSeconds  = DateTimeUtil.getFormattedTime(6000)
        val expected    = "00:06"
        assertEquals(expected,sixSeconds)
    }

    @Test
    fun shouldCalculateAndShowTimeOneMinuteAndThirtySeconds(){
        val sixSeconds  = DateTimeUtil.getFormattedTime(90000)
        val expected    = "01:30"
        assertEquals(expected,sixSeconds)
    }

    @Test
    fun shouldCalculateAndShowTimeZero(){
        val sixSeconds  = DateTimeUtil.getFormattedTime(100)
        val expected    = "00:00"
        assertEquals(expected,sixSeconds)
    }

    @Test
    fun shouldFormatADate(){
        val date        = Date(1612177200000) // 01/02/2021 as 08:00
        val actual      = DateTimeUtil.formatADate(date,EnumDatePattern.DD_MM_YYYY_HH_MM)
        val expected    = "01/02/2021 08:00"
        assertEquals(expected,actual)
    }

    @Test
    fun shouldAddDaysToADate(){
        val date        = Date(1612177200000) // 01/02/2021 as 08:00
        val tomorrow    = DateTimeUtil.plusDay(date,1)
        val expected    = "02/02/2021 08:00"
        val actual      = DateTimeUtil.formatADate(tomorrow,EnumDatePattern.DD_MM_YYYY_HH_MM)
        assertEquals(expected,actual)
    }

    @Test
    fun shouldFormattedTimeBetweenToTimesInMiliseconds(){
        val _25_minutes = 1_500_000L
        val _12_minutes = 720_000L
        val expected    = "13:00"
        val actual      = DateTimeUtil.getFormattedTime(_25_minutes,_12_minutes)
        assertEquals(expected,actual)
    }

    @Test
    fun shouldGetOnlyTheDateWithNoHourAndMinute(){
        val today   = Date(1612179000000) // 01/02/2021
        val actual  = DateTimeUtil.formatADate(DateTimeUtil.onlyDate( today))

        assertEquals("01/02/2021 00:00",actual)
    }

    @Test
    fun shouldCalculateDiffBetweenOneDateWithNow(){
        val now = Date(1612179000000) // 01/02/2021 as 08:30:00
        val date = Date(1612178400000) // 01/02/2021 as 08:20:00
        val dateDiff = DateTimeUtil.differenceBetweenDateNow(date,now)
        assertEquals(0,dateDiff.days)
        assertEquals(0,dateDiff.hours)
        assertEquals(10,dateDiff.minutes)
    }

    @Test
    fun shouldGetTimePassedDescription(){
        val now     = Date(1612179000000) // 01/02/2021 as 08:30:00
        val date    = Date(1612178400000) // 01/02/2021 as 08:20:00
        val actual  = DateTimeUtil.getDatePassedDescription(date, now)
        val expected= "10m"
        assertEquals(expected,actual)
    }


}