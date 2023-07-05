package com.kakaoent.md.application.api.product

import com.kakaoent.md.domain.product.Product
import java.time.Instant

class ProductsResponse(
    val products: List<ProductView>,
    val hasMore: Boolean,
)

data class ProductView(
    val productId: String,
    val name: String,
    val status: Product.Status,
    val price: Long,
    val displayedAt: Instant,
    val thumbnailImageUrl: String,
    val badges: ProductBadges
) {
    companion object {
        fun from(product: Product): ProductView = ProductView(
            productId = product.productId,
            name = "상품 이름",
            status = product.status,
            price = product.price,
            displayedAt = product.displayedAt,
            thumbnailImageUrl = "상품 이미지 주소",
            badges = ProductBadges(
                isNew = product.isNew,
            )
        )
    }
}

data class ProductBadges(
    val isNew: Boolean,
)
