package com.kakaoent.md.application.api.product

import com.kakaoent.md.PagingParams
import com.kakaoent.md.domain.product.Product
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ProductController {

    @GetMapping(GET_PRODUCTS)
    fun getProducts(
        @RequestParam("mallId", required = true) mallId: String,
        @RequestParam("tags") tags: List<String> = emptyList(),
        @RequestParam("sort") sortOption: ProductSortOption = ProductSortOption.RECENT,
        pagingParam: PagingParams,
    ): ProductsResponse {
        return ProductsResponse(
            products = listOf(
                ProductView(
                    productId = "4012345678",
                    name = "[상품] 정말 비싼 상품",
                    status = Product.Status.ON_SALE,
                    price = 5000,
                    displayedAt = Instant.now(),
                    thumbnailImageUrl = "https://sandbox-fan-static.melon.com/md/2023/7/3/image.png",
                    badges = ProductBadges(
                        isNew = true,
                    )
                ),
                ProductView(
                    productId = "4012345678",
                    name = "[상품] 정말 싼 상품",
                    status = Product.Status.SOLD_OUT,
                    price = 5000,
                    displayedAt = Instant.now(),
                    thumbnailImageUrl = "https://sandbox-fan-static.melon.com/md/2023/7/3/image.png",
                    badges = ProductBadges(
                        isNew = true,
                    )
                )
            ),
            hasMore = false
        )
    }

    companion object {
        const val GET_PRODUCTS = "/products"
    }
}
