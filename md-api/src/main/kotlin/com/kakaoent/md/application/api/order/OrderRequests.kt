package com.kakaoent.md.application.api.order

data class CheckoutRequest(
    val memberKey: Long,
    val orderProducts: List<OrderProduct>,
    val paymentMethod: String,
    val deliveryInfo: DeliveryInfo
)

data class OrderProduct(
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
