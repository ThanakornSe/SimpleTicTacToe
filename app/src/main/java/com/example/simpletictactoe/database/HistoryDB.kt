package com.example.simpletictactoe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simpletictactoe.model.History

@Database(entities = [History::class], version = 1)
@TypeConverters(Converter::class)
abstract class HistoryDB : RoomDatabase() {

    abstract val historyDao: HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDB? = null

        fun getDatabase(context: Context): HistoryDB {
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDB::class.java,
                    "player_db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}