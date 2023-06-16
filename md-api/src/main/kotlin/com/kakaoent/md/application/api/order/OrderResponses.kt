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

data class CancelOrderResponse(
    val memberKey: Long,
    val orderId: String,
    val cancelProducts: List<CancelProduct>,
    val cancelRequestedAt: Instant
)

data class PurchaseHistoryResponse(
    val memberKey: Long,
    val orders: List<OrderResponse>
)

data class OrderResponse(
    val orderId: String,
    val purchasedProducts: List<PurchasedProductResponse>,
    val paymentAmount: Int,
    val orderAt: Instant
)

data class PurchasedProductResponse(
    val productId: String,
    val productName: String,
    val price: Int,
    val quantity: Int
)

data class PurchaseDetailResponse(
    val memberKey: Long,
    val orderId: String,
    val items: List<PurchasedItemDetailResponse>,
    val paymentMethod: PaymentMethod,
    val paymentAmount: Int,
    val orderAt: Instant,
    val shippingInfo: ShippingInfo
)

data class PurchasedItemDetailResponse(
    val productId: String,
    val productName: String,
    val price: Int,
    val quantity: Int
)

data class ShippingInfo(
    val address: String,
    val postalCode: String
)

enum class PaymentMethod {
    CARD,
    BANK_TRANSFER,
    MOBILE
}
