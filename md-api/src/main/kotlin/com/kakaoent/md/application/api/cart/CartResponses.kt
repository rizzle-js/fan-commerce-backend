package com.kakaoent.md.application.api.cart

import java.time.Instant

data class CartResponse(
    val cartId: String,
    val memberKey: Long,
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
    val cartId: String,
    val memberKey: Long,
    val productId: String,
    val name: String,
    val price: Int,
    val addedAt: Instant,
    val quantity: Int
)

data class UpdateProductQuantityInCartResponse(
    val cartId: String,
    val memberKey: Long,
    val productId: String,
    val name: String,
    val updatedQuantity: Int,
)

data class EmptyCartResponse(
    val cartId: String,
    val memberKey: Long,
    val emptiedAt: Instant,
)

data class RemoveProductFromCartResponse(
    val cartId: String,
    val memberKey: Long,
    val productId: String,
    val name: String,
)
