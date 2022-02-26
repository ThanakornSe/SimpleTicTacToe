package com.example.simpletictactoe.repository

import androidx.lifecycle.LiveData
import com.example.simpletictactoe.model.History

interface HistoryRepository {

    suspend fun insertHistory(history: History)

    suspend fun deleteHistory(history: History)

    suspend fun updateHistory(history: History)

    suspend fun clearHistory()

    fun getAllHistory(): LiveData<List<History>>

}