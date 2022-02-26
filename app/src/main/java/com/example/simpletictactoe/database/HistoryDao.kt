package com.example.simpletictactoe.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpletictactoe.model.History

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

    @Update
    suspend fun updateHistory(history: History)

    @Query("DELETE FROM history_table")
    suspend fun clearHistory()

    @Query("SELECT * FROM history_table ORDER BY id DESC")
    fun getAllHistory(): LiveData<List<History>>
}