package com.superplay.pomodoroapp.ui.main

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import com.superplay.pomodoroapp.repository.PomodoroRespository
import com.superplay.pomodoroapp.services.AlarmService
import com.superplay.pomodoroapp.util.DateTimeUtil
import com.superplay.pomodoroapp.util.INITIAL_TIME
import kotlinx.coroutines.launch
import java.util.*


class NewPomodoroViewModel(private val pomodoroRespository: PomodoroRespository) : ViewModel() {
    val count           = MutableLiveData<String>()
    val canStop         = MutableLiveData<Boolean>()
    var countDownTimer : CountDownTimer ?= null
    var lastTimer      : Long           ?= null
    var lastId         : Long           ?= null
    val alarmService: AlarmService by lazy {
        AlarmService()
    }
    fun startCountDown(){
        viewModelScope.launch {
            lastId = pomodoroRespository.save(PomodoroDTO(0, INITIAL_TIME,0L,Date()))
        }

        canStop.postValue(true)
        countDownTimer = object: CountDownTimer(INITIAL_TIME, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                lastTimer = millisUntilFinished
                count.postValue(DateTimeUtil.getFormattedTime(millisUntilFinished))
            }
            override fun onFinish() {
                count.postValue("00:00")
                canStop.value   = false
                lastTimer       = 0L
                registerStopCountDown()
            }
        }
        countDownTimer?.start()
    }

    fun stopCountDown(){
        countDownTimer?.cancel()
        canStop.value = false
        registerStopCountDown()
    }

    private fun registerStopCountDown() {
        viewModelScope.launch {
            val pomodoroDTO = pomodoroRespository.find(lastId!!)
            pomodoroDTO.finalTime = lastTimer!!
            pomodoroRespository.update(pomodoroDTO)
        }
    }



}