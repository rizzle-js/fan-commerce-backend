package com.kakaoent.md.application.api.order

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class OrderController {
    @PostMapping(PROCESS_CHECKOUT)
    fun processCheckout(
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

    @PostMapping(CANCEL_ORDER)
    fun cancelOrder(
        @PathVariable orderId: String,
        @RequestBody request: CancelOrderRequest
    ): CancelOrderResponse {
        return CancelOrderResponse(
            memberKey = request.memberKey,
            orderId = orderId,
            cancelProducts = request.cancelProducts.map {
                CancelProduct(
                    productId = it.productId,
                    productName = it.productName,
                    cancelPrice = it.cancelPrice,
                    cancelQuantity = it.cancelQuantity
                )
            },
            cancelRequestedAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    companion object {
        const val PROCESS_CHECKOUT = "/orders"
        const val CANCEL_ORDER = "/orders/{orderId}/cancellation"
    }
}