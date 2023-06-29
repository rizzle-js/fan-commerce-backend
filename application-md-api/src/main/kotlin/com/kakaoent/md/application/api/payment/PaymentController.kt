package com.kakaoent.md.application.api.payment

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class PaymentController(
    private val paymentService: PaymentService
) {
    @GetMapping(GET_PAYMENT_METHODS)
    fun getPaymentMethods(
        @RequestParam memberKey: Long
    ): PaymentMethodResponse {
        // TODO: 결제요청페이지 직접 개발(커스터마이징) 시, memberKey를 통한 회원정보 조회 후 결제수단을 제공해야 함(DOMESTIC / GLOBAL)
        return paymentService.getPaymentMethod(memberKey)
    }

    @PostMapping(REQUEST_PAYMENT)
    fun requestPayment(
        @RequestBody request: PaymentRequest
    ): PaymentResponse {
        // Logic to call the service and handle exceptions
        return PaymentResponse(
            memberKey = request.memberKey,
            orderId = request.orderId,
            paymentId = "0rKZoejXFUmw8KrLjahxu6",
            paymentMethodId = request.paymentMethodId,
            amount = request.amount,
            requestedAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    @PostMapping(CANCEL_PAYMENT)
    fun cancelPayment(
        @PathVariable paymentId: String,
        @RequestBody request: CancelPaymentRequest
    ): CancelPaymentResponse {
        // Logic to call the service and handle exceptions
        return CancelPaymentResponse(
            memberKey = request.memberKey,
            orderId = request.orderId,
            paymentId = paymentId,
            cancelledAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    companion object {
        const val GET_PAYMENT_METHODS = "/payment/methods"
        const val REQUEST_PAYMENT = "/payments"
        const val CANCEL_PAYMENT = "/payments/{paymentId}/cancellation"
    }
}
