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
    val price: Int,
    val displayedAt: Instant,
    val thumbnailImageUrl: String,
    val badges: ProductBadges
)

data class ProductBadges(
    val isNew: Boolean,
)
