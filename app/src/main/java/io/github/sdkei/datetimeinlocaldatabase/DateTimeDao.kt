package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.time.Instant

@Dao
interface DateTimeDao {
    @Insert
    suspend fun insertDateTimeEntity(dateTimeEntity: DateTimeEntity)

    /**
     * 指定された日時より前の [DateTimeEntity] の数を返す。
     */
    @Query("SELECT count(*) FROM DateTimeEntity WHERE DateTimeEntity.dateTime < :dateTime")
    suspend fun countDateTimeEntitiesBefore(dateTime: Instant): Int

    /**
     * 指定された日時より後の [DateTimeEntity] の数を返す。
     */
    @Query("SELECT count(*) FROM DateTimeEntity WHERE DateTimeEntity.dateTime > :dateTime")
    suspend fun countDateTimeEntitiesAfter(dateTime: Instant): Int

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
