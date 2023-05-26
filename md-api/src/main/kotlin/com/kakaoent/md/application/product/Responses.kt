package com.kakaoent.md.application.product

data class ProductDetailResponse(
    val productId: Long,
    val name: String,
    val price: Int,
    val tags: List<String>,
    val productImageUrl: String,
)
