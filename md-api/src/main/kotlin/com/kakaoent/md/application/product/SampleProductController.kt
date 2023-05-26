package com.kakaoent.md.application.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class SampleProductController {

    @GetMapping("/{productId}")
    fun getProductDetail(@PathVariable productId: Long): ProductDetailResponse {
        return ProductDetailResponse(
            productId = productId,
            name = "테스트 상품",
            price = 10000,
            tags = listOf("태그1", "태그2"),
            productImageUrl = "https://image.com"
        )
    }
}
