package com.superplay.pomodoroapp.util


//import androidx.databinding.BindingConversion

enum class BooleanToVisibleConverter {
    VISIBLE,
    INVISIBLE,
    GONE;
    //@BindingConversion
    fun booleanToVisibility(value:Boolean) = if(value) VISIBLE else GONE
}
