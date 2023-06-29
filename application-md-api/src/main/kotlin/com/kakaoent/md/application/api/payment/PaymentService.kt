package com.kakaoent.md.application.api.payment

import org.springframework.stereotype.Service


@Service
class PaymentService {
    fun getPaymentMethod(memberKey: Long): PaymentMethodResponse {
        return PaymentMethodResponse(
            memberKey,
            listOf(
                PaymentMethod(PaymentOption.DKPG)
            )
        )

    }
}

data class PaymentMethod(
    val name: PaymentOption
)

enum class PaymentOption {
    DKPG
}