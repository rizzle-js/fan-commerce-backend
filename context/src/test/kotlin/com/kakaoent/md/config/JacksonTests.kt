package com.kakaoent.md.config

import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals

class JacksonTests {
    @Test
    fun `test`() {
        val expectData = """{"memberKey":1,"productId":"1","createAt":1686641320}"""
        val beforeData = TestClass(1L, "1", Instant.ofEpochSecond(1686641320L))
        assertEquals(
            expectData,
            objectMapper.writeValueAsString(beforeData)
        )
        val afterData = objectMapper.readValue(expectData, TestClass::class.java)
        assertEquals(beforeData, afterData)
    }

    data class TestClass(
        val memberKey: Long,
        val productId: String,
        val createAt: Instant
    )
}