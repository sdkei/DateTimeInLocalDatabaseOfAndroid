package io.github.sdkei.datetimeinlocaldatabase

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class DateTimeDaoTest {
    private lateinit var database: DateTimeDatabase
    private lateinit var dao: DateTimeDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DateTimeDatabase::class.java
        ).build()
        dao = database.dateTimeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    /**
     * SQL の `datetime` 関数の返値を確認する。
     */
    @Test
    fun test_datetime() {
        assertThat(dao.datetimeAd0Minus1(), `is`("-001-12-31 23:59:59"))
        assertThat(dao.datetimeAd0(), `is`("0000-01-01 00:00:00"))
        assertThat(dao.datetimeAd9999(), `is`("9999-12-31 23:59:59"))
        assertThat(dao.datetimeAd9999Plus1(), `is`(null as String?))
    }
}
