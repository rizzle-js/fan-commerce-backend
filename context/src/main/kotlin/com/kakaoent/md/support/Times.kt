package com.kakaoent.md.support

import jakarta.persistence.Embeddable
import java.time.Instant

@Embeddable
data class Period(
    val startedAt: Instant,
    val endedAt: Instant,
) {
    init {
        require(startedAt.isBefore(endedAt) || startedAt == endedAt) { "startedAt must be before endedAt" }
    }

    companion object {
        val ALWAYS: Period = Period(startedAt = MIN_INSTANT, endedAt = MAX_INSTANT)
    }
}

val MIN_INSTANT: Instant = Instant.EPOCH

val MAX_INSTANT: Instant = Instant.ofEpochSecond(95617584000)
