package com.kakaoent.md.config

import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals

class JacksonTests {
    @Test
    fun test() {
        val expectData = """{"memberKey":1,"productId":"1","createAt":1686641320}"""
        val beforeData = TestClass(1L, "1", Instant.ofEpochSecond(1686641320L))
        assertEquals(
            expectData,
            objectMapper.writeValueAsString(beforeData)
        )
        val afterData: TestClass = objectMapper.readValue(expectData)
        assertEquals(beforeData, afterData)
    }

    data class TestClass(
        val memberKey: Long,
        val productId: String,
        val createAt: Instant
    )
}