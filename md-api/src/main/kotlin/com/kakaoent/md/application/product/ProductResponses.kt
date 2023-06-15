package com.kakaoent.md.application.product

import java.time.Instant

data class ProductsResponse(
    val products: List<Product>
)

data class Product(
    val productId: String,
    val name: String,
    val status: ProductStatus,
    val quantity: Int,
    val type: ProductType,
    val price: Int,
    val tags: List<String>,
    val productImageUrl: String
)

enum class ProductStatus {
    ON_SALE,
    SOLD_OUT,
    END_OF_SALE
}

enum class ProductType {
    NORMAL,
    BUNDLE
}

data class ProductDetailResponse(
    val productId: String,
    val name: String,
    val status: ProductStatus,
    val type: ProductType,
    val relatedArtistId: String,
    val relatedArtistName: String,
    val relatedTicketId: String,
    val relatedTicketName: String,
    val price: Int,
    val tags: List<String>,
    val description: String,
    val productImageUrl: String,
    val stock: Int,
    val shippingInfo: ShippingType
)

enum class ShippingType {
    NORMAL,
    FAST,
    FREE,
    PICKUP,
    EXPRESS,
}

data class ProductAvailabilityResponse(
    val productId: String,
    val status: ProductStatus
)

data class ProductCancellationResponse(
    val productId: String,
    val cancellationPossible: Boolean
)

data class PurchasePermissionResponse(
    val memberKey: Long,
    val productId: String,
    val purchasePossible: Boolean
)

data class PurchaseProductResponse(
    val memberKey: Long,
    val productId: String,
    val quantity: Int,
    val purchaseAt: Instant,
    val paymentMethod: PaymentMethod,
    val paymentAmount: Int,
    val deliveryAddress: String
)

data class CancelProductResponse(
    val memberKey: Long,
    val productId: String,
    val reason: String,
    val cancellationAt: Instant,
    val refundMethod: RefundMethod,
    val refundAmount: Int,
    val refundStatus: RefundStatus
)

enum class RefundStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED
}

data class ProductRatesResponse(
    val productRates: List<ProductRate>
)

data class ProductRate(
    val rateId: String,
    val memberKey: Long,
    val productId: String,
    val rate: Int,
    val comment: String,
    val ratedAt: Instant
)

data class ProductRateResponse(
    val memberKey: Long,
    val productId: String,
    val rateId: String,
    val ratedAt: Instant
)

data class ProductRateDetailResponse(
    val memberKey: Long,
    val productId: String,
    val rateId: String,
    val comment: String,
    val rate: Int,
    val ratedAt: Instant,
    val reviewImages: List<String>
)
