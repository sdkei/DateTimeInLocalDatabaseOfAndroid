package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class DateTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateTime: Instant,
)
