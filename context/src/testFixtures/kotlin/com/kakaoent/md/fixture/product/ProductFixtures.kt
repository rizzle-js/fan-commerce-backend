package com.kakaoent.md.fixture.product

import com.kakaoent.md.domain.mall.MallProduct
import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.domain.product.ProductOption
import com.kakaoent.md.domain.product.Receiving
import com.kakaoent.md.support.Period
import java.math.BigDecimal
import java.time.Instant

fun product(
    productId: String,
    price: Long = 0,
    name: String = "상품 이름",
    description: String = "상품 설명",
    feeRate: BigDecimal = BigDecimal.ZERO,
    associatedMelonTicketId: String? = null,
    limitPurchaseQuantity: Long? = null,
    thumbnailImageUrl: String = "상품 이미지 주소",
    salesPeriod: Period = Period.ALWAYS,
    displayType: Product.DisplayType = Product.DisplayType.DISPLAY,
    displayedAt: Instant = Instant.EPOCH,
    status: Product.Status = Product.Status.ON_SALE,
    productOptions: List<ProductOption> = emptyList(),
    receiving: Receiving = Receiving(type = Receiving.Type.PICK_UP),
): Product = Product(
    productId = productId,
    price = price,
    name = name,
    description = description,
    feeRate = feeRate,
    thumbnailImageUrl = thumbnailImageUrl,
    displayedAt = displayedAt,
    salesPeriod = salesPeriod,
    status = status,
    receiving = receiving,
).apply {
    productOptions.onEach {
        addProductOption(it)
    }
}

fun productOption(
    value: String,
): ProductOption = ProductOption(
    value = value
)

fun mallProduct(
    mallId: String,
    productId: String,
): MallProduct = MallProduct(
    mallId = mallId,
    productId = productId
)
