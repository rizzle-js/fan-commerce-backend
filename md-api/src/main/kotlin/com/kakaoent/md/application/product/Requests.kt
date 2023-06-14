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

data class CancelProductRequest(
    val memberKey: Long,
    val productId: String,
    val reason: String,
    val refundMethod: RefundMethod
)

enum class RefundMethod {
    ORIGINAL_PAYMENT_METHOD,
    STORE_CREDIT
}
