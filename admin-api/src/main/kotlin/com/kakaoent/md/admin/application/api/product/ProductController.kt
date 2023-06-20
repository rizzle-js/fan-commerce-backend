package com.kakaoent.md.admin.application.api.product

import com.kakaoent.md.PagingParams
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ProductController {
    @GetMapping(GET_PRODUCTS)
    fun getProducts(
        params: PagingParams
    ): GetProductsResponse {
        return GetProductsResponse(
            listOf(
                Product(
                    productId = "3BHPWD54nkHkaSlQSYY1eB",
                    name = "상품1",
                    price = 1000,
                    quantity = 100,
                    status = ProductStatus.ON_SALE,
                    registeredAt = Instant.now()
                ),
                Product(
                    productId = "13JP4ICmLxeBnPFse0CvwZ",
                    name = "상품2",
                    price = 2000,
                    quantity = 100,
                    status = ProductStatus.ON_SALE,
                    registeredAt = Instant.now()
                )
            )
        )
    }

    @GetMapping(GET_PRODUCT_DETAIL)
    fun getProductDetail(
        @PathVariable productId: String
    ): GetProductDetailResponse {
        return GetProductDetailResponse(
            productId = productId,
            name = "상품1",
            price = 1000,
            quantity = 100,
            status = ProductStatus.ON_SALE,
            description = "상품1의 상세 설명",
            registeredAt = Instant.now()
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT_DETAIL = "/products/{productId}"
    }
}