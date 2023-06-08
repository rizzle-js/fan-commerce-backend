package com.kakaoent.md.application.product

import com.kakaoent.md.UuidGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping(GET_PRODUCTS)
    fun getProducts(
        @RequestParam channelId: String
    ): ProductsResponse {
        return ProductsResponse(
            listOf(
                ProductLine(
                    productId = UuidGenerator.generate(),
                    name = "상품1",
                    status = ProductStatus.ON_SALE,
                    quantity = 100,
                    type = ProductType.NORMAL,
                    price = 1000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image1.jpg"
                ),
                ProductLine(
                    productId = UuidGenerator.generate(),
                    name = "상품2",
                    status = ProductStatus.ON_SALE,
                    quantity = 100,
                    type = ProductType.NORMAL,
                    price = 2000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image2.jpg"
                )
            )
        )
    }

    @GetMapping(GET_PRODUCTS_DETAIL)
    fun getProduct(@PathVariable id: String): ProductDetailResponse {
        return ProductDetailResponse(
            productId = UuidGenerator.generate(),
            name = "상품1",
            status = ProductStatus.ON_SALE,
            quantity = 100,
            type = ProductType.NORMAL,
            relatedArtistId = 1,
            relatedArtistName = "아티스트1",
            relatedTicketId = null,
            relatedTicketName = null,
            price = 1000,
            tags = listOf("태그1", "태그2"),
            productDescription = "상품 설명",
            productImageUrl = "https://example.com/image1.jpg",
            productInventory = 100,
            productShippingInfo = ShippingType.NORMAL
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCTS_DETAIL = "/products/{id}"
    }
}
