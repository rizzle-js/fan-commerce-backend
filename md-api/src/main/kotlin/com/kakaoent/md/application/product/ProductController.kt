package com.kakaoent.md.application.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping(GET_PRODUCTS)
    fun getProducts(): GetProductsResponse {
        return GetProductsResponse(
            listOf(
                ProductLine(
                    productId = 1,
                    name = "상품1",
                    price = 1000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image1.jpg"
                ),
                ProductLine(
                    productId = 2,
                    name = "상품2",
                    price = 2000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image2.jpg"
                ),
                ProductLine(
                    productId = 3,
                    name = "상품3",
                    price = 3000,
                    tags = listOf("태그2", "태그3"),
                    productImageUrl = "https://example.com/image3.jpg"
                ),
            )
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT_DETAIL = "/products/{productId}"
    }
}
