package com.kakaoent.md

import java.time.*

data class ZonedDateTimePeriod(
    val begin: ZonedDateTime,
    val end: ZonedDateTime
) {
    init {
        require(begin.isBeforeOrEqual(end))
    }
}

fun ZonedDateTime.withIn(period: ZonedDateTimePeriod): Boolean = with(period) {
    isAfterOrEqual(begin) && isBeforeOrEqual(end)
}

val MIN_ZONED_DATE_TIME: ZonedDateTime = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC"))
val MAX_ZONED_DATE_TIME: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(250_000_000_000), ZoneId.of("UTC"))

fun ZonedDateTime.isBeforeOrEqual(other: ZonedDateTime): Boolean = isBefore(other) || isEqual(other)
fun ZonedDateTime.isAfterOrEqual(other: ZonedDateTime): Boolean = isAfter(other) || isEqual(other)

fun birth(month: Int, day: Int): String {
    require(day in 1..31 && month in 1..12)
    val filledDay = day.toString().padStart(2, '0')
    val filledMonth = month.toString().padStart(2, '0')
    return "${filledMonth}.${filledDay}"
}

fun String.isBirth(): Boolean = matches(Regex("""\d{2}\.\d{2}"""))
