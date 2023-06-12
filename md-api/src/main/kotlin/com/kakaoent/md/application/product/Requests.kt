package com.kakaoent.md.application.product

data class PurchaseProductRequest(
    val memberKey: Long,
    val productId: String,
    val quantity: Int,
    val paymentMethod: PaymentMethod,
    val deliveryAddress: String
)

enum class PaymentMethod {
    CARD,
    BANK_TRANSFER,
    MOBILE_PAYMENT
}
