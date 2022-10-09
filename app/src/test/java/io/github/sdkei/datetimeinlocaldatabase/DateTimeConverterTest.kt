package io.github.sdkei.datetimeinlocaldatabase

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.DateTimeException
import java.time.OffsetDateTime
import java.time.ZoneOffset

class DateTimeConverterTest {
    /** 境界値テスト。 */
    @Test
    fun test_instantToString_boundaryValue() {
        // 下限
        OffsetDateTime.of(0, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
            .toInstant()
            .let { DateTimeConverter.instantToString(it) }
            .also { assertThat(it, `is`("0000-01-01 00:00:00")) }
        // 上限（秒未満は切り捨て）
        OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 999_999_999, ZoneOffset.UTC)
            .toInstant()
            .let { DateTimeConverter.instantToString(it) }
            .also { assertThat(it, `is`("9999-12-31 23:59:59")) }

        // 下限より下
        OffsetDateTime.of(-1, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC)
            .toInstant()
            .let {
                runCatching {
                    DateTimeConverter.instantToString(it)
                }
            }.also {
                assertThat(it.exceptionOrNull(), `is`(instanceOf(DateTimeException::class.java)))
            }
        // 上限より上
        OffsetDateTime.of(10000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
            .toInstant()
            .let {
                runCatching {
                    DateTimeConverter.instantToString(it)
                }
            }.also {
                assertThat(it.exceptionOrNull(), `is`(instanceOf(DateTimeException::class.java)))
            }
    }

    @Test
    fun test_stringToInstant() {
        // 下限
        DateTimeConverter.stringToInstant("0000-01-01 00:00:00")
            .also {
                assertThat(
                    it,
                    `is`(OffsetDateTime.of(0, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC).toInstant())
                )
            }
        // 上限
        DateTimeConverter.stringToInstant("9999-12-31 23:59:59")
            .also {
                assertThat(
                    it,
                    `is`(OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC).toInstant())
                )
            }
    }
}
