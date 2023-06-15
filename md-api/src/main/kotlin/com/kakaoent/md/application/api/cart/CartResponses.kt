package com.kakaoent.md.application.api.cart

import java.time.Instant

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

data class AddProductToCartResponse(
    val memberKey: Long,
    val productId: String,
    val name: String,
    val price: Int,
    val addedAt: Instant,
    val cartId: String,
    val quantity: Int
)

data class UpdateProductQuantityInCartResponse(
    val memberKey: Long,
    val productId: String,
    val name: String,
    val updatedQuantity: Int,
    val updatedAt: Instant,
    val cartId: String
)
