package com.kakaoent.md.application.product

data class ProductsResponse(
    val products: List<Product>
)

data class Product(
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
    val type: ProductType,
    val relatedArtistId: String,
    val relatedArtistName: String,
    val relatedTicketId: String,
    val relatedTicketName: String,
    val price: Int,
    val tags: List<String>,
    val description: String,
    val productImageUrl: String,
    val stock: Int,
    val shippingInfo: String
)

enum class ShippingType {
    NORMAL,
    FAST,
    FREE,
    PICKUP,
    EXPRESS,
}
