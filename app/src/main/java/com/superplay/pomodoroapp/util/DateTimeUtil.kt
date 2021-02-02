package com.superplay.pomodoroapp.util

import com.superplay.pomodoroapp.model.DifferenceOfDates
import com.superplay.pomodoroapp.model.EnumDatePattern
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

object DateTimeUtil {

    fun getFormattedTime(milis:Long) : String{
        return if(milis > 0){
            if(milis / (1000 * 60 ) > 0){
                //Has minutes
                val minutes = milis / 1000 / 60
                val seconds = milis / 1000 % 60
                "${padingResult(minutes)}:${padingResult(seconds)}"
            }else{
                //Has only seconds
                "00:${padingResult(milis/1000)}"
            }
        }else{
            "00:00"
        }
    }

    fun padingResult(value: Long) : String{
        return "$value".padStart(2,'0')
    }

    fun getFormattedTime(iniTimeInMillis:Long, finalTimeMillis:Long) : String{
        return getFormattedTime(iniTimeInMillis - finalTimeMillis)
    }


    fun formatADate(date: Date, pattern: EnumDatePattern = EnumDatePattern.DD_MM_YYYY_HH_MM) : String{
        val simpleDateFormat = SimpleDateFormat(pattern.pattern)
        return simpleDateFormat.format(date)
    }

    fun plusDay(date: Date, amountOfDays:Int): Date{
        return Calendar.getInstance().apply {
            time = date
            add(Calendar.DATE,amountOfDays)
        }.time
    }

    fun minusDay(date: Date, amountOfDays: Int) = plusDay(date,amountOfDays * -1)

    fun onlyDate(date: Date): Date{
        return Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR,0)
            set(Calendar.MINUTE,0)
            set(Calendar.SECOND,0)
            set(Calendar.MILLISECOND,0)
        }.time
    }

    fun differenceBetweenDateNow(date: Date, now: Date): DifferenceOfDates{
        val difference = now.time - date.time
        val seconds = difference    / 1000
        val minutes = seconds       / 60
        val hours   = minutes       / 60
        val days    = hours         / 24
        return DifferenceOfDates(
            seconds = seconds.toInt(),
            minutes = minutes.toInt(),
            hours   = hours.toInt(),
            days    = days.toInt()
        )
    }

    fun getDatePassedDescription(date: Date, now: Date): String{
        val differece = differenceBetweenDateNow(date,now)
        return if(differece.days > 0){
            if(differece.days == 1){
               "yesterday"
            }else{
                "old"
            }
        }else{
            if(differece.hours > 0){
                "${differece.hours}h ago"
            }else{
                "${differece.minutes}m ago"
            }
        }

    }

    fun now() = Date()
}