package com.kakaoent.md.application.api.delivery

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class DeliveryController {
    @PostMapping(PICKUP)
    fun pickupProduct(
        @RequestBody request: PickupRequest
    ): PickupResponse {
        // Logic to call the service and handle exceptions
        return PickupResponse(
            memberKey = request.memberKey,
            orderId = request.orderId,
            productId = request.productId,
            productName = "상품명", // this should be fetched from a service
            quantity = request.quantity,
            pickedUpAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    companion object {
        const val PICKUP = "/delivery/pickup"
    }
}