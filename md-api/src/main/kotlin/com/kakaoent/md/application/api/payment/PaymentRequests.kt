package com.kakaoent.md.application.api.payment

data class PaymentRequest(
    val memberKey: Long,
    val orderId: String,
    val paymentMethodId: String,
    val amount: Int
)

data class CompletePaymentRequest(
    val memberKey: Long,
    val orderId: String,
    val paymentMethodId: String,
    val amount: Int
)
