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

    @PostMapping(REQUEST_PAYMENT)
    fun requestPayment(
        @RequestBody request: PaymentRequest
    ): PaymentResponse {
        // Logic to call the service and handle exceptions
        return PaymentResponse(
            memberKey = request.memberKey,
            orderId = request.orderId,
            paymentMethodId = request.paymentMethodId,
            amount = request.amount,
            requestedAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    companion object {
        const val GET_PAYMENT_METHODS = "/payment/methods"
        const val REQUEST_PAYMENT = "/payments"
    }
}
