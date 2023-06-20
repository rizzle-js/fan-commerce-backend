package com.kakaoent.md.admin.application.api.product

import java.time.Instant

data class GetProductsResponse(
    val products: List<Product>
)

data class Product(
    val productId: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: ProductStatus,
    val registeredAt: Instant
)

enum class ProductStatus {
    ON_SALE,
    SOLD_OUT,
    END_OF_SALE
}

data class GetProductDetailResponse(
    val productId: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: ProductStatus,
    val description: String,
    val registeredAt: Instant
)
