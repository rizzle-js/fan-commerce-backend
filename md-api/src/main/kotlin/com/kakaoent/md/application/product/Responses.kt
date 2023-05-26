package com.kakaoent.md.application.product

data class GetProductsResponse(
    val products: List<ProductLine>
)

data class ProductLine(
    val productId: Long,
    val name: String,
    val price: Int,
    val tags: List<String>,
    val productImageUrl: String
)
