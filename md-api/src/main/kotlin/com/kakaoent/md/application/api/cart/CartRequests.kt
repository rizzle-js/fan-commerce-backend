package com.kakaoent.md.application.api.cart

data class AddProductToCartRequest(
    val memberKey: Long,
    val productId: String,
    val quantity: Int
)

data class UpdateProductQuantityInCartRequest(
    val memberKey: Long,
    val productId: String,
    val quantity: Int
)

data class RemoveProductFromCartRequest(
    val memberKey: Long,
    val productId: String
)
