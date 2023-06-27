package com.kakaoent.md.application.api.product

import com.kakaoent.md.PagingParams
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
        @RequestParam memberKey: Long,
    ): PurchasePermissionResponse {
        return PurchasePermissionResponse(
            memberKey = memberKey,
            productId = productId,
            purchasePossible = true
        )
    }

    @PostMapping(PURCHASE_PRODUCT)
    fun purchaseProduct(
        @PathVariable productId: String,
        @RequestBody request: PurchaseProductRequest
    ): PurchaseProductResponse {
        return PurchaseProductResponse(
            memberKey = request.memberKey,
            productId = productId,
            quantity = request.quantity,
            purchaseAt = Instant.ofEpochSecond(1686641320L),
            paymentMethod = request.paymentMethod,
            paymentAmount = 10000,
            deliveryAddress = request.deliveryAddress
        )
    }

    @PostMapping(CANCEL_PRODUCT)
    fun cancelProduct(
        @PathVariable productId: String,
        @RequestBody request: CancelProductRequest
    ): CancelProductResponse {
        return CancelProductResponse(
            memberKey = request.memberKey,
            productId = productId,
            reason = request.reason,
            cancellationAt = Instant.ofEpochSecond(1686641320L),
            refundMethod = request.refundMethod,
            refundAmount = 10000, // This should be determined by business logic
            refundStatus = RefundStatus.PENDING // Initial refund status
        )
    }

    @GetMapping(GET_PRODUCT_REVIEWS)
    fun getProductReviews(
        @PathVariable productId: String,
        params: PagingParams
    ): ProductReviewsResponse {
        return ProductReviewsResponse(
            productReviews = listOf(
                ProductReview(
                    reviewId = "2fBs3IHgpHecTZHuGg43u2",
                    memberKey = 1L,
                    productId = productId,
                    rate = 4,
                    comment = "좋아요 :)",
                    reviewAt = Instant.ofEpochSecond(1686641320L),
                ),
                ProductReview(
                    reviewId = "07AmdtM3GtyWOFug7i8Fog",
                    memberKey = 2L,
                    productId = productId,
                    rate = 5,
                    comment = "만족합니다.",
                    reviewAt = Instant.ofEpochSecond(1686641920L),
                )
            )
        )
    }

    @PostMapping(REVIEW_PRODUCT)
    fun reviewProduct(
        @PathVariable productId: String,
        @RequestBody request: ReviewProductRequest
    ): ProductReviewResponse {
        // 상품 평가 로직은 생략
        return ProductReviewResponse(
            memberKey = request.memberKey,
            productId = productId,
            reviewId = "07ADpf6TfRU1wYU88Q6KP8",
            reviewAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    @GetMapping(GET_PRODUCT_REVIEW_DETAIL)
    fun getProductReviewDetail(
        @PathVariable productId: String,
        @PathVariable reviewId: String,
    ): ProductReviewDetailResponse {
        return ProductReviewDetailResponse(
            memberKey = 1234L,
            productId = productId,
            reviewId = "07ADpf6TfRU1wYU88Q6KP8",
            comment = "좋아요 :)",
            rate = 10,
            reviewAt = Instant.ofEpochSecond(1686641320L),
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
        const val CHECK_PRODUCT_AVAILABILITY = "/products/{productId}/availability"
        const val CHECK_PRODUCT_CANCELLATION = "/products/{productId}/cancellation"
        const val CHECK_PURCHASE_PERMISSION = "/products/{productId}/purchase/permission"
        const val PURCHASE_PRODUCT = "/products/{productId}/purchase"
        const val CANCEL_PRODUCT = "/products/{productId}/cancellation"
        const val GET_PRODUCT_REVIEWS = "/products/{productId}/reviews"
        const val REVIEW_PRODUCT = "/products/{productId}/reviews"
        const val GET_PRODUCT_REVIEW_DETAIL = "/products/{productId}/reviews/{reviewId}"
    }
}
