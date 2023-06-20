package com.kakaoent.md.admin.application.api.product

data class RegisterProductRequest(
    val name: String,
    val price: Int,
    val quantity: Int,
    val status: ProductStatus
)
