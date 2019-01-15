package com.akingyin.androidjetpackdemo.data.db.converter

import androidx.room.TypeConverter
import java.util.*





/**
 * @ Description:
 * @author king
 * @ Date 2019/1/15 15:37
 * @version V1.0
 */
class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}