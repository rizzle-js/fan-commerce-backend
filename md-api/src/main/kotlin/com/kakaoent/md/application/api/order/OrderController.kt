package com.kakaoent.md.application.api.order

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class OrderController {
    @PostMapping(CHECKOUT)
    fun checkout(
        @RequestBody checkoutRequest: CheckoutRequest
    ): CheckoutResponse {
        return CheckoutResponse(
            memberKey = checkoutRequest.memberKey,
            orderId = "4iat8YRCAFycmqVJydbEHH",
            orderProducts = checkoutRequest.orderProducts,
            paymentMethod = checkoutRequest.paymentMethod,
            totalPayment = checkoutRequest.orderProducts.sumOf { it.price * it.quantity },
            checkedOutAt = Instant.ofEpochSecond(1686641320L),
            deliveryInfo = checkoutRequest.deliveryInfo
        )
    }

    companion object {
        const val CHECKOUT = "/checkout"
    }
}