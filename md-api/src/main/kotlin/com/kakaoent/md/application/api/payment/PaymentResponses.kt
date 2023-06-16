package com.kakaoent.md.application.api.payment

data class PaymentMethodResponse(
    val memberKey: Long,
    val paymentMethods: List<PaymentMethod>
)

data class PaymentMethod(
    val name: String,
    val details: String
)
