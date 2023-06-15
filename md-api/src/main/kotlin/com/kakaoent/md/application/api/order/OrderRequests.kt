package com.kakaoent.md.application.api.order

data class CheckoutRequest(
    val memberKey: Long,
    val orderItems: List<OrderItem>,
    val paymentMethod: String,
    val deliveryInfo: DeliveryInfo
)

data class OrderItem(
    val productId: String,
    val productName: String,
    val price: Int,
    val quantity: Int
)

data class DeliveryInfo(
    val recipientName: String,
    val recipientPhone: String,
    val deliveryAddress: String
)
