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

    @GetMapping(GET_PURCHASE_HISTORY)
    fun getPurchaseHistory(
        @RequestParam memberKey: Long
    ): PurchaseHistoryResponse {
        // Logic to call the service and handle exceptions
        return PurchaseHistoryResponse(
            memberKey = memberKey,
            orders = listOf(
                OrderResponse(
                    orderId = "order123",
                    purchasedProducts = listOf(
                        PurchasedProductResponse(
                            productId = "product123",
                            productName = "상품1",
                            price = 10000,
                            quantity = 2
                        )
                    ),
                    paymentAmount = 20000,
                    orderAt = Instant.ofEpochSecond(1686641320L)
                )
                // ... More orders
            )
        )
    }

    @GetMapping(GET_PURCHASE_DETAIL)
    fun getPurchaseDetail(
        @PathVariable orderId: String,
        @RequestParam memberKey: Long,
    ): PurchaseDetailResponse {
        // Logic to call the service and handle exceptions
        return PurchaseDetailResponse(
            memberKey = memberKey,
            orderId = orderId,
            items = listOf(
                PurchasedItemDetailResponse(
                    productId = "product123",
                    productName = "상품1",
                    price = 10000,
                    quantity = 2
                )
                // ... More items
            ),
            paymentMethod = PaymentMethod.CARD,
            paymentAmount = 20000,
            orderAt = Instant.ofEpochSecond(1686641320L),
            shippingInfo = ShippingInfo(
                address = "서울시 중구",
                postalCode = "100-000"
            )
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

    @GetMapping(DOWNLOAD_RECEIPT)
    fun downloadReceipt(
        @PathVariable orderId: String,
        @RequestParam memberKey: Long,
    ): ReceiptDownloadResponse {
        return ReceiptDownloadResponse(
            memberKey = memberKey,
            orderId = orderId,
            downloadAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    companion object {
        const val PROCESS_CHECKOUT = "/orders"
        const val GET_PURCHASE_HISTORY = "/orders"
        const val CANCEL_ORDER = "/orders/{orderId}/cancellation"
        const val GET_PURCHASE_DETAIL = "/orders/{orderId}"
        const val DOWNLOAD_RECEIPT = "/orders/{orderId}/receipt"
    }
}