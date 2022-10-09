package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DateTimeDao {
    @Insert
    suspend fun insertDateTimeEntity(dateTimeEntity: DateTimeEntity)

    @Query("SELECT datetime('now')")
    fun datetimeNow(): String

    @Query("SELECT datetime('0000-01-01 00:00:00', '-1 seconds')")
    fun datetimeAd0Minus1(): String

    @Query("SELECT datetime('0000-01-01 00:00:00')")
    fun datetimeAd0(): String

    @Query("SELECT datetime('9999-12-31 23:59:59')")
    fun datetimeAd9999(): String

    @Query("SELECT datetime('9999-12-31 23:59:59', '+1 seconds')")
    fun datetimeAd9999Plus1(): String
}
