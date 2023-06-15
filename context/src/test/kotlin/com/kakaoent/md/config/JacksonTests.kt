package com.kakaoent.md.config

import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.Instant

class JacksonTests : FunSpec() {
    init {
        test("기본 테스트") {
            val expectData = """{"memberKey":1,"productId":"1","createAt":1686641320}"""
            val beforeData = TestClass(1L, "1", Instant.ofEpochSecond(1686641320L))
            objectMapper.writeValueAsString(beforeData) shouldBe expectData
            val afterData: TestClass = objectMapper.readValue(expectData)
            afterData shouldBe beforeData
        }
    }

    data class TestClass(
        val memberKey: Long,
        val productId: String,
        val createAt: Instant
    )
}