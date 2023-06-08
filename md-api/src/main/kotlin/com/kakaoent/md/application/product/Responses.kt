package com.kakaoent.md.application.product

data class ProductsResponse(
    val products: List<ProductLine>
)

data class ProductLine(
    val productId: String,
    val name: String,
    val status: ProductStatus,
    val quantity: Int,
    val type: ProductType,
    val price: Int,
    val tags: List<String>,
    val productImageUrl: String
)

enum class ProductStatus {
    ON_SALE,
    SOLD_OUT,
    END_OF_SALE
}

enum class ProductType {
    NORMAL,
    BUNDLE
}

data class ProductDetailResponse(
    val productId: String,
    val name: String,
    val status: ProductStatus,
    val quantity: Int,
    val type: ProductType,
    val price: Int,
    val tags: List<String>,
    val productImageUrl: String,
    val relatedArtistId: Long?,
    val relatedArtistName: String?,
    val relatedTicketId: Long?,
    val relatedTicketName: String?,
    val productDescription: String,
    val productInventory: Int,
    val productShippingInfo: ShippingType,
)

enum class ShippingType {
    NORMAL,
    FAST,
    FREE,
    PICKUP,
    EXPRESS,
}
