package com.kakaoent.md.fixture.product

import com.kakaoent.md.domain.mall.MallProduct
import com.kakaoent.md.domain.product.*
import com.kakaoent.md.support.Period
import java.math.BigDecimal
import java.time.Instant

fun product(
    productId: String,
    price: Long = 0,
    name: String = "상품 이름",
    description: String = "상품 설명",
    feeRate: BigDecimal = BigDecimal.ZERO,
    purchaseLimits: Int? = null,
    salesPeriod: Period = Period.ALWAYS,
    displayedAt: Instant? = Instant.EPOCH,
    displayType: Displaying.DisplayType = Displaying.DisplayType.DISPLAY,
    status: Product.Status = Product.Status.ON_SALE,
    productOptionGroups: List<ProductOptionGroup> = emptyList(),
    images: List<ProductImage> = emptyList(),
    receiving: Receiving = Receiving(type = Receiving.Type.PICK_UP),
    badge: Badge = Badge(true),
    certification: Certification = Certification.none(),
    tax: Tax = Tax.taxFree(),
    production: Production = Production.alwaysOn(),
    salesInformationDisclosure: SalesInformationDisclosure = SalesInformationDisclosure.empty(),
): Product = Product(
    productId = productId,
    price = price,
    name = name,
    description = description,
    feeRate = feeRate,
    purchaseLimits = purchaseLimits,
    displaying = Displaying(displayType = displayType, displayedAt = displayedAt),
    salesPeriod = salesPeriod,
    status = status,
    receiving = receiving,
    badge = badge,
    certification = certification,
    tax = tax,
    production = production,
    salesInformationDisclosure = salesInformationDisclosure,
).apply {
    productOptionGroups.onEach {
        addProductOptionGroup(it)
    }
    images.onEach {
        addProductImage(it)
    }
}

fun productOptionGroup(
    name: String,
    productOptions: List<ProductOption> = emptyList(),
): ProductOptionGroup = ProductOptionGroup(
    name = name,
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
