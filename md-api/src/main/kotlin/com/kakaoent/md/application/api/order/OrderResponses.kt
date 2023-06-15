package com.kakaoent.md.application.api.order

import java.time.Instant

data class CheckoutResponse(
    val memberKey: Long,
    val orderId: String,
    val orderProducts: List<OrderProduct>,
    val paymentMethod: String,
    val totalPayment: Int,
    val checkedOutAt: Instant,
    val deliveryInfo: DeliveryInfo
)
