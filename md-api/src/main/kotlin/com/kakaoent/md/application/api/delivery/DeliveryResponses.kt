package com.kakaoent.md.application.api.delivery

import java.time.Instant

data class PickupResponse(
    val memberKey: Long,
    val orderId: String,
    val productId: String,
    val productName: String,
    val quantity: Int,
    val pickedUpAt: Instant
)
