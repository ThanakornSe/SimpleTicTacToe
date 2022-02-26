package com.example.simpletictactoe.repository

import androidx.lifecycle.LiveData
import com.example.simpletictactoe.database.HistoryDao
import com.example.simpletictactoe.model.History

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository {
    override suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    override suspend fun clearHistory() {
        historyDao.clearHistory()
    }

    override suspend fun deleteHistory(history: History) {
        historyDao.deleteHistory(history)
    }

    override suspend fun updateHistory(history: History) {
        historyDao.updateHistory(history)
    }

    override fun getAllHistory(): LiveData<List<History>> {
        return historyDao.getAllHistory()
    }
}