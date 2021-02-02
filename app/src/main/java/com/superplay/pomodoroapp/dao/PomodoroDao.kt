package com.superplay.pomodoroapp.dao

import androidx.room.*
import com.superplay.pomodoroapp.model.Pomodoro

@Dao
interface PomodoroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pomodoro:Pomodoro) : Long
    @Update
    suspend fun update(pomodoro:Pomodoro)
    @Delete
    suspend fun delete(pomodoro: Pomodoro)
    @Query("SELECT * FROM pomodoro WHERE id = :id")
    suspend fun getPomodoro(id:Long):Pomodoro
    @Query("SELECT * FROM pomodoro")
    suspend fun findAll():Array<Pomodoro>

}