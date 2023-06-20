package com.kakaoent.md.admin.application.api.product

import com.kakaoent.md.PagingParams
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ProductAdminController {
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
                    registeredAt = Instant.ofEpochSecond(1686641320L)
                ),
                Product(
                    productId = "13JP4ICmLxeBnPFse0CvwZ",
                    name = "상품2",
                    price = 2000,
                    quantity = 100,
                    status = ProductStatus.ON_SALE,
                    registeredAt = Instant.ofEpochSecond(1686641320L)
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
            registeredAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @PostMapping(REGISTER_PRODUCT)
    fun registerProduct(
        @RequestBody request: RegisterProductRequest
    ): RegisterProductResponse {
        val productId = "13JP4ICmLxeBnPFse0CvwZ"
        return RegisterProductResponse(
            productId = productId,
            name = request.name,
            price = request.price,
            quantity = request.quantity,
            status = request.status,
            registeredAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @PutMapping(UPDATE_PRODUCT)
    fun updateProduct(
        @PathVariable productId: String,
        @RequestBody request: UpdateProductRequest
    ): UpdateProductResponse {
        return UpdateProductResponse(
            productId = productId,
            name = request.name,
            price = request.price,
            quantity = request.quantity,
            status = request.status,
            updatedAt = Instant.now()
        )
    }

    @DeleteMapping(DELETE_PRODUCT)
    fun deleteProduct(
        @PathVariable productId: String
    ): DeleteProductResponse {
        return DeleteProductResponse(
            productId = productId,
            deletedAt = Instant.now()
        )
    }

    @GetMapping(GET_PRODUCT_REVIEW)
    fun getProductReview(
        @PathVariable productId: Long
    ): ProductReviewResponse {
        // 상품의 평가를 조회하는 로직은 이곳에 작성
        // 여기서는 예시로 임의의 값을 리턴하도록 함
        return ProductReviewResponse(
            productId = productId,
            memberKey = 1L,
            rating = 4.5,
            review = "This is a great product!",
            reviewAt = Instant.now()
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT_DETAIL = "/products/{productId}"
        const val REGISTER_PRODUCT = "/products"
        const val UPDATE_PRODUCT = "/products/{productId}"
        const val DELETE_PRODUCT = "/products/{productId}"
        const val GET_PRODUCT_REVIEW = "/products/{productId}/review"
    }
}