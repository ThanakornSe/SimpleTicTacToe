package com.example.simpletictactoe.database

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

}