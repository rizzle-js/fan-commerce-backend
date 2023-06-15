package com.kakaoent.md.application.api.order

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class OrderController {
    @PostMapping(CHECKOUT)
    fun checkout(
        @RequestBody checkoutRequest: CheckoutRequest
    ): CheckoutResponse {
        // 주문 ID를 생성하고, 결제를 진행하고, 체크아웃을 완료하고 결과를 반환하는
        // 비즈니스 로직이 필요하며, 이는 상황에 따라 달라집니다.
        val orderId = "4iat8YRCAFycmqVJydbEHH"
        val totalPayment = checkoutRequest.orderItems.sumOf { it.price * it.quantity }
        val checkedOutAt = Instant.now()
        return CheckoutResponse(
            memberKey = checkoutRequest.memberKey,
            orderId = orderId,
            orderItems = checkoutRequest.orderItems,
            paymentMethod = checkoutRequest.paymentMethod,
            totalPayment = totalPayment,
            checkedOutAt = checkedOutAt,
            deliveryInfo = checkoutRequest.deliveryInfo
        )
    }

    companion object {
        const val CHECKOUT = "/checkout"
    }
}