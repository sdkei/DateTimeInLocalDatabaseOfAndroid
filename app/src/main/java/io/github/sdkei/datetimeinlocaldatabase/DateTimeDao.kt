package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface DateTimeDao {
    @Insert
    suspend fun insertDateTimeEntity(dateTimeEntity: DateTimeEntity)
}
