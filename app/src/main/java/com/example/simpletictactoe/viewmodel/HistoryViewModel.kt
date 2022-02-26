package com.example.simpletictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletictactoe.model.History
import com.example.simpletictactoe.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    fun insert(history: History) {
        viewModelScope.launch {
            repository.insertHistory(history)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    fun update(history: History) {
        viewModelScope.launch {
            repository.updateHistory(history)
        }
    }

    fun delete(history: History) {
        viewModelScope.launch {
            repository.deleteHistory(history)
        }
    }

    val getAllHistory = repository.getAllHistory()

}