package com.kakaoent.md.application.product

data class GetProductsResponse(
    val products: List<ProductLine>
)

data class ProductLine(
    val productId: Long,
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
