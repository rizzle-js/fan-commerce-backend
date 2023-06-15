package com.kakaoent.md.application.api.cart

data class CartResponse(
    val memberKey: Long,
    val cartId: String,
    val products: List<CartProduct>
)

data class CartProduct(
    val productId: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val productImageUrl: String
)
