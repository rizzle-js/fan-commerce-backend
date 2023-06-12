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
                Product(
                    productId = UuidGenerator.generate(),
                    name = "상품1",
                    status = ProductStatus.ON_SALE,
                    quantity = 100,
                    type = ProductType.NORMAL,
                    price = 1000,
                    tags = listOf("태그1", "태그2"),
                    productImageUrl = "https://example.com/image1.jpg"
                ),
                Product(
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

    @GetMapping(GET_PRODUCT_DETAIL)
    fun getProductDetail(@PathVariable productId: String): ProductDetailResponse {
        return ProductDetailResponse(
            productId = productId,
            name = "상품1",
            status = ProductStatus.ON_SALE,
            type = ProductType.NORMAL,
            relatedArtistId = "Artist1",
            relatedArtistName = "아티스트1",
            relatedTicketId = "Ticket1",
            relatedTicketName = "티켓1",
            price = 1000,
            tags = listOf("태그1", "태그2"),
            description = "상품 설명",
            productImageUrl = "https://example.com/image1.jpg",
            stock = 100,
            shippingInfo = ShippingType.PICKUP
        )
    }

    @GetMapping(CHECK_PRODUCT_AVAILABILITY)
    fun checkProductAvailability(
        @PathVariable productId: String
    ): ProductAvailabilityResponse {
        return ProductAvailabilityResponse(
            productId = productId,
            status = ProductStatus.ON_SALE
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT_DETAIL = "/products/{productId}"
        const val CHECK_PRODUCT_AVAILABILITY = "/product/availability/{productId}"
    }
}
