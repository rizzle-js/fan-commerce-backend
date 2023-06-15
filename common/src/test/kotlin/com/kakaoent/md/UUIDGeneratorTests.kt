package com.kakaoent.md

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe

class UUIDGeneratorTests  : FunSpec() {
    init {
        test("UUID 생성 테스트") {
            val uuid = UuidGenerator.generate()
            uuid shouldNotBe null
            println(uuid)
        }
    }
}