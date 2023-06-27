package com.kakaoent.md.application.api.cart

data class AddProductToCartRequest(
    val productId: String,
    val quantity: Int
)

data class UpdateProductQuantityInCartRequest(
    val quantity: Int
)
