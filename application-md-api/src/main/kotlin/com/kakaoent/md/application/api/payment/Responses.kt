package com.kakaoent.md.application.api.payment

import java.time.Instant

data class PaymentMethodResponse(
    val memberKey: Long,
    val paymentMethods: List<PaymentMethod>
)

data class PaymentResponse(
    val memberKey: Long,
    val orderId: String,
    val paymentId: String,
    val paymentMethodId: String,
    val amount: Int,
    val requestedAt: Instant
)

data class CancelPaymentResponse(
    val memberKey: Long,
    val orderId: String,
    val paymentId: String,
    val cancelledAt: Instant
)
