package com.kakaoent.md.application.api.product

import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.domain.product.Receiving
import com.kakaoent.md.domain.product.SalesInformationDisclosure
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
            displayedAt = product.displaying.displayedAt,
            thumbnailImageUrl = product.thumbnailImage?.imageUrl ?: "todo default image",
            badges = ProductBadges(
                isNew = product.isNew,
            )
        )
    }
}

data class ProductResponse(
    val productId: String,
    val productImages: List<ImageView>,
    val name: String,
    val price: Long,
    val purchaseLimits: Int?,
    val optionGroups: List<ProductOptionGroupView>,
    val receiving: ReceivingView,
    val badges: ProductBadges,
    val detail: ProductDetailView,
    val salesInformation: SalesInformationView,
)

data class ProductOptionGroupView(
    val productOptionGroupId: Long,
    val name: String,
    val options: List<ProductOptionView>,
)

data class ProductOptionView(
    val productOptionId: Long,
    val value: String,
    val salesStatus: SalesProduct.SalesStatus? = null,
)

data class ReceivingView(
    val type: Receiving.Type,
    val receivingDates: List<ReceivingDateView>
)

data class ReceivingDateView(
    val date: LocalDate,
    val purchasedTicket: Boolean = false
)

data class ProductBadges(
    val isNew: Boolean,
)

data class ImageView(
    val imageUrl: String,
)

data class ProductDetailView(
    val content: String,
    val detailImages: List<ImageView>,
    val sizes: String,
)

data class SalesInformationView(
    val seller: String,
    val sellingInformation: String,
    val productInformation: String,
    val tradeTerm: String,
    val deliveryTerm: String,
    val caution: String,
) {
    companion object {
        fun from(salesInformation: SalesInformationDisclosure): SalesInformationView = SalesInformationView(
            seller = salesInformation.seller,
            sellingInformation = salesInformation.sellingInformation,
            productInformation = salesInformation.productInformation,
            tradeTerm = salesInformation.tradeTerm,
            deliveryTerm = salesInformation.deliveryTerm,
            caution = salesInformation.caution,
        )
    }
}
