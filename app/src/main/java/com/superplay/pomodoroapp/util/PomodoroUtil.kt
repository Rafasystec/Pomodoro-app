package com.superplay.pomodoroapp.util

import com.superplay.pomodoroapp.model.*
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import java.util.*

object PomodoroUtil {

    fun getListPomodoroDTOFromPomodorList(listOfPomodoros :Array<Pomodoro>) : List<PomodoroDTO>{
        val resultList : MutableList<PomodoroDTO> = mutableListOf()
        listOfPomodoros.forEach {
            resultList.add(it.toPomodoroDTO())
        }
        return resultList;
    }

    fun groupPomodoroByDate(listOfPomodoros: List<PomodoroDTO>, today:Date = Date()) : PomodoroGroup{
        val listToday       :MutableList<PomodoroDTO> = mutableListOf()
        val listYesterday   :MutableList<PomodoroDTO> = mutableListOf()
        val listOld         :MutableList<PomodoroDTO> = mutableListOf()

        val yesterday   =   DateTimeUtil.onlyDate(DateTimeUtil.minusDay(today , 1))
        println("Yesterday : ${DateTimeUtil.formatADate(yesterday)}")


        if(listOfPomodoros.isNotEmpty()){
            listOfPomodoros.forEach {
                println(DateTimeUtil.formatADate(it.date, EnumDatePattern.DD_MM_YYYY_HH_MM))
                when {
                    it.date.after(yesterday) -> {
                        if(listToday.isEmpty()){
                            it.isTheFirst = true
                        }
                        it.day = EnumPomodoroDay.TODAY
                        listToday.add(it)
                    }
                    it.date.before(yesterday) -> {
                        if(listOld.isEmpty()){
                            it.isTheFirst = true
                        }
                        it.day  = EnumPomodoroDay.OLD
                        listOld.add(it)
                    }
                    else -> {
                        if(listYesterday.isEmpty()){
                            it.isTheFirst = true
                        }
                        it.day = EnumPomodoroDay.YESTERDAY
                        listYesterday.add(it)
                    }
                }
            }
        }
        return PomodoroGroup(listToday,listYesterday,listOld)
    }

    fun groupAndOrderAPomodorList(listOfPomodoros: List<PomodoroDTO>, today:Date = Date()) : List<PomodoroDTO>{
        val grouping    = groupPomodoroByDate(listOfPomodoros,today)
        val finalList   = mutableListOf<PomodoroDTO>()
        grouping.listToday.forEach {
            finalList.add(it)
        }
        grouping.listYesterday.forEach {
            finalList.add(it)
        }
        grouping.listOld.forEach {
            finalList.add(it)
        }
        return finalList
    }
}