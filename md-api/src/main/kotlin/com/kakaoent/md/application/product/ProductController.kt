package com.kakaoent.md.application.product

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ProductController {

    @GetMapping(GET_PRODUCTS)
    fun getProducts(
        @RequestParam channelId: String
    ): ProductsResponse {
        return ProductsResponse(
            listOf(
                Product(
                    productId = "3h2BHrmKAuABuHedFdH7XT",
                    name = "상품1",
                    status = ProductStatus.ON_SALE,
                    quantity = 100,
                    type = ProductType.NORMAL,
                    price = 1000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image1.jpg"
                ),
                Product(
                    productId = "3h2BHrmKAuABuHedFdH7XT",
                    name = "상품2",
                    status = ProductStatus.ON_SALE,
                    quantity = 100,
                    type = ProductType.NORMAL,
                    price = 2000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image2.jpg"
                )
            )
        )
    }

    @GetMapping(GET_PRODUCT_DETAIL)
    fun getProductDetail(@PathVariable productId: String): ProductDetailResponse {
        return ProductDetailResponse(
            productId = productId,
            name = "상품1",
            status = ProductStatus.ON_SALE,
            type = ProductType.NORMAL,
            relatedArtistId = "Artist1",
            relatedArtistName = "아티스트1",
            relatedTicketId = "Ticket1",
            relatedTicketName = "티켓1",
            price = 1000,
            tags = listOf("태그1", "태그2"),
            description = "상품 설명",
            productImageUrl = "https://example.com/image1.jpg",
            stock = 100,
            shippingInfo = ShippingType.PICKUP
        )
    }

    @GetMapping(CHECK_PRODUCT_AVAILABILITY)
    fun checkProductAvailability(
        @PathVariable productId: String
    ): ProductAvailabilityResponse {
        return ProductAvailabilityResponse(
            productId = productId,
            status = ProductStatus.ON_SALE
        )
    }

    @GetMapping(CHECK_PRODUCT_CANCELLATION)
    fun checkProductCancellation(
        @PathVariable productId: String
    ): ProductCancellationResponse {
        return ProductCancellationResponse(
            productId = productId,
            cancellationPossible = true
        )
    }

    @GetMapping(CHECK_PURCHASE_PERMISSION)
    fun checkPurchasePermission(
        @PathVariable productId: String,
        @PathVariable memberKey: Long,
    ): PurchasePermissionResponse {
        return PurchasePermissionResponse(
            memberKey = memberKey,
            productId = productId,
            purchasePossible = true
        )
    }

    @PostMapping(PURCHASE_PRODUCT)
    fun purchaseProduct(
        @RequestBody request: PurchaseProductRequest
    ): PurchaseProductResponse {
        return PurchaseProductResponse(
            memberKey = request.memberKey,
            productId = request.productId,
            quantity = request.quantity,
            purchaseAt = Instant.ofEpochSecond(1686641320L),
            paymentMethod = request.paymentMethod,
            paymentAmount = 10000,
            deliveryAddress = request.deliveryAddress
        )
    }

    @PostMapping(CANCEL_PRODUCT)
    fun cancelProduct(
        @RequestBody request: CancelProductRequest
    ): CancelProductResponse {
        return CancelProductResponse(
            memberKey = request.memberKey,
            productId = request.productId,
            reason = request.reason,
            cancellationAt = Instant.ofEpochSecond(1686641320L),
            refundMethod = request.refundMethod,
            refundAmount = 10000, // This should be determined by business logic
            refundStatus = RefundStatus.PENDING // Initial refund status
        )
    }

    @PostMapping(RATE_PRODUCT)
    fun rateProduct(
        @RequestBody request: RateProductRequest
    ): RateProductResponse {
        // 상품 평가 로직은 생략
        return RateProductResponse(
            memberKey = request.memberKey,
            productId = request.productId,
            rateId = "07ADpf6TfRU1wYU88Q6KP8",
            ratedAt = Instant.now()
        )
    }

    @GetMapping(RATE_PRODUCT_DETAIL)
    fun rateProductDetail(
        @PathVariable rateId: String,
    ): RateProductDetailResponse {
        return RateProductDetailResponse(
            memberKey = 1234L,
            productId = "3h2BHrmKAuABuHedFdH7XT",
            rateId = "rateId",
            comment = "좋아요 :)",
            rate = 10,
            ratedAt = Instant.now(),
            reviewImages = listOf(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg"
            )
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT_DETAIL = "/products/{productId}"
        const val CHECK_PRODUCT_AVAILABILITY = "/products/availability/{productId}"
        const val CHECK_PRODUCT_CANCELLATION = "/products/cancellation/{productId}"
        const val CHECK_PURCHASE_PERMISSION = "/products/purchase/permission/{productId}/{memberKey}"
        const val PURCHASE_PRODUCT = "/products/purchase"
        const val CANCEL_PRODUCT = "/products/cancel"
        const val RATE_PRODUCT = "/products/rate"
        const val RATE_PRODUCT_DETAIL = "/products/rate/{rateId}"
    }
}
