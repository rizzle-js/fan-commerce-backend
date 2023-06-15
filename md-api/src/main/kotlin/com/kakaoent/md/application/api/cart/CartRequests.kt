package com.kakaoent.md.application.api.cart

data class AddProductToCartRequest(
    val memberKey: Long,
    val productId: String,
    val quantity: Int
)
