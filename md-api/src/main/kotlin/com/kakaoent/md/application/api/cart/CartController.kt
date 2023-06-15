package com.kakaoent.md.application.api.cart

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class CartController {
    @GetMapping(GET_CART)
    fun getCart(
        @RequestParam memberKey: Long
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
        @RequestBody request: AddProductToCartRequest
    ): AddProductToCartResponse {
        return AddProductToCartResponse(
            memberKey = request.memberKey,
            productId = request.productId,
            name = "상품명",
            price = 1000,
            addedAt = Instant.ofEpochSecond(1686641320L),
            cartId = "0WpdogcEJ4jlc9UwIc0kNm",
            quantity = request.quantity
        )
    }

    companion object {
        const val GET_CART = "/cart"
        const val ADD_PRODUCT_TO_CART = "/cart"
    }
}