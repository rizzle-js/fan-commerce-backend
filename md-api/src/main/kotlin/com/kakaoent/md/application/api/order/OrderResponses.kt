package com.kakaoent.md.application.api.order

import java.time.Instant

data class CheckoutResponse(
    val memberKey: Long,
    val orderId: String,
    val orderItems: List<OrderItem>,
    val paymentMethod: String,
    val totalPayment: Int,
    val checkedOutAt: Instant,
    val deliveryInfo: DeliveryInfo
)
