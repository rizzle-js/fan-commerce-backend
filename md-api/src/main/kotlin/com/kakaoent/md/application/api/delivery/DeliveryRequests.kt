package com.kakaoent.md.application.api.delivery

data class PickupRequest(
    val memberKey: Long,
    val orderId: String,
    val productId: String,
    val quantity: Int
)
