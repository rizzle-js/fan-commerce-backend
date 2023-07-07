package com.kakaoent.md.application.api.product

import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.domain.product.Receiving
import com.kakaoent.md.domain.product.SalesProduct
import com.kakaoent.md.support.Language
import java.time.Instant
import java.time.LocalDate

class ProductsResponse(
    val products: List<ProductView>,
    val hasMore: Boolean,
)

data class ProductView(
    val productId: String,
    val name: String,
    val status: Product.Status,
    val price: Long,
    val displayedAt: Instant?,
    val thumbnailImageUrl: String,
    val badges: ProductBadges
) {
    companion object {
        fun of(product: Product, language: Language = Language.KOREAN): ProductView = ProductView(
            productId = product.productId,
            name = product.getName(language),
            status = product.status,
            price = product.price,
            displayedAt = product.displayedAt,
            thumbnailImageUrl = product.thumbnailImageUrl,
            badges = ProductBadges(
                isNew = product.isNew,
            )
        )
    }
}

data class ProductResponse(
    val productId: String,
    val name: String,
    val price: Long,
    val options: List<ProductOptionView>,
    val receiving: ReceivingView,
)

data class ProductOptionView(
    val productOptionId: Long,
    val value: String,
)

data class ReceivingView(
    val type: Receiving.Type,
    val receivingDates: List<ReceivingDateView>
)

data class ReceivingDateView(
    val date: LocalDate,
)

data class ProductBadges(
    val isNew: Boolean,
)
