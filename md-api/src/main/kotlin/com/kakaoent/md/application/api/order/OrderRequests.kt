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

data class PartialCancelOrderRequest(
    val memberKey: Long,
    val orderId: String,
    val cancelProducts: List<CancelProduct>
)

data class CancelProduct(
    val productId: String,
    val productName: String,
    val cancelPrice: Int,
    val cancelQuantity: Int
)
