package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        DateTimeEntity::class,
    ],
)
@TypeConverters(
    DateTimeConverter::class,
)
abstract class DateTimeDatabase : RoomDatabase() {
    abstract fun dateTimeDao(): DateTimeDao
}
