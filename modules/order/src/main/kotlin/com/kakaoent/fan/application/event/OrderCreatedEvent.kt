package com.kakaoent.fan.application.event

import java.time.Instant

data class OrderCreatedEvent(
    val id: Long,
    val orderId: Long,
    val createdAt: Instant = Instant.now()
)
