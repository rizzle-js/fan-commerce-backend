package com.kakaoent.md

import com.github.f4b6a3.uuid.UuidCreator
import com.github.f4b6a3.uuid.codec.base.Base62Codec

object UuidGenerator {
    fun generate(): String = Base62Codec.INSTANCE.encode(UuidCreator.getRandomBased())
    fun gen() = UuidCreator.getTimeOrdered()
}

fun main() {
    repeat(100) {
        println(UuidGenerator.generate())
    }
}
