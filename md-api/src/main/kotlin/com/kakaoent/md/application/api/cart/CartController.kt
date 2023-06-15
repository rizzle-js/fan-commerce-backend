package com.kakaoent.md.application.api.cart

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class CartController {
    @GetMapping(GET_CART)
    fun getCart(
        @PathVariable memberKey: Long
    ): CartResponse {
        return CartResponse(
            memberKey = memberKey,
            cartId = "0WpdogcEJ4jlc9UwIc0kNm",
            products = listOf(
                CartProduct(
                    productId = "7gLUgb896KkRgpnnHsNH3x",
                    name = "상품1",
                    price = 1000,
                    quantity = 1,
                    productImageUrl = "https://example.com/image1.jpg"
                ),
                CartProduct(
                    productId = "6OcVhBmT4k60OHxnHYBshl",
                    name = "상품2",
                    price = 2000,
                    quantity = 2,
                    productImageUrl = "https://example.com/image2.jpg"
                )
            )
        )
    }

    @PostMapping(ADD_PRODUCT_TO_CART)
    fun addProductToCart(
        @PathVariable memberKey: Long,
        @RequestBody request: AddProductToCartRequest
    ): AddProductToCartResponse {
        return AddProductToCartResponse(
            memberKey = memberKey,
            productId = request.productId,
            name = "상품명",
            price = 1000,
            addedAt = Instant.ofEpochSecond(1686641320L),
            cartId = "0WpdogcEJ4jlc9UwIc0kNm",
            quantity = request.quantity
        )
    }

    @PutMapping(UPDATE_PRODUCT_QUANTITY_IN_CART)
    fun updateProductQuantityInCart(
        @PathVariable memberKey: Long,
        @PathVariable productId: String,
        @RequestBody request: UpdateProductQuantityInCartRequest
    ): UpdateProductQuantityInCartResponse {
        return UpdateProductQuantityInCartResponse(
            memberKey = memberKey,
            productId = productId,
            name = "상품명",
            updatedQuantity = request.quantity,
            updatedAt = Instant.ofEpochSecond(1686641320L),
            cartId = "0WpdogcEJ4jlc9UwIc0kNm"
        )
    }

    @DeleteMapping(REMOVE_PRODUCT_FROM_CART)
    fun removeProductFromCart(
        @PathVariable memberKey: Long,
        @PathVariable productId: String
    ): RemoveProductFromCartResponse {
        return RemoveProductFromCartResponse(
            memberKey = memberKey,
            productId = productId,
            name = "상품명",
            removedAt = Instant.ofEpochSecond(1686641320L),
            cartId = "0WpdogcEJ4jlc9UwIc0kNm"
        )
    }

    @DeleteMapping(CLEAN_CART)
    fun cleanCart(
        @PathVariable memberKey: Long
    ): EmptyCartResponse {
        return EmptyCartResponse(
            memberKey = memberKey,
            emptiedAt = Instant.ofEpochSecond(1686641320L),
            cartId = "0WpdogcEJ4jlc9UwIc0kNm"
        )
    }

    companion object {
        const val GET_CART = "/carts/{memberKey}"
        const val ADD_PRODUCT_TO_CART = "/carts/{memberKey}/products"
        const val UPDATE_PRODUCT_QUANTITY_IN_CART = "/carts/{memberKey}/products/{productId}"
        const val REMOVE_PRODUCT_FROM_CART = "/carts/{memberKey}/products/{productId}"
        const val CLEAN_CART = "/carts/{memberKey}"
    }
}