package com.kakaoent.md.application.api.payment

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class PaymentController {
    @GetMapping(GET_PAYMENT_METHODS)
    fun getPaymentMethods(
        @RequestParam memberKey: Long
    ): PaymentMethodResponse {
        // Logic to call the service and handle exceptions
        return PaymentMethodResponse(
            memberKey = memberKey,
            paymentMethods = listOf(
                PaymentMethod(
                    name = "신용카드",
                    details = "Visa, MasterCard, AMEX"
                ),
                PaymentMethod(
                    name = "무통장 입금",
                    details = "국민은행, 신한은행, 우리은행"
                )
            )
        )
    }

    companion object {
        const val GET_PAYMENT_METHODS = "/payment/methods"
    }
}