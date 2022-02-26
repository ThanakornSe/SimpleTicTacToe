package com.example.simpletictactoe.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val player1: String,
    val player2: String,
    val boardSize: String,
    val playerWon: String = "",
    val date: Date = Calendar.getInstance().time
)
