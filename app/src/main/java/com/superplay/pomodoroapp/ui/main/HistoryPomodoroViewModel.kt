package com.superplay.pomodoroapp.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superplay.pomodoroapp.model.dto.PomodoroDTO
import com.superplay.pomodoroapp.repository.PomodoroRespository
import com.superplay.pomodoroapp.util.PomodoroUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryPomodoroViewModel(private val pomodoroRespository: PomodoroRespository) : ViewModel() {

    val showProgress            = ObservableBoolean()
    val pomodoroListLiveData    = MutableLiveData<List<PomodoroDTO>>()

    fun getPomodoros(){

        viewModelScope.launch {
            showProgress.set(true)
            val findAll = pomodoroRespository.findAll()
            val groupedPomodoroDTO = PomodoroUtil.groupAndOrderAPomodorList(findAll)
            pomodoroListLiveData.value = groupedPomodoroDTO
            showProgress.set(false)
        }
    }

}