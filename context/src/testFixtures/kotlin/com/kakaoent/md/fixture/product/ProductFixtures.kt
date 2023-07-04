package com.kakaoent.md.fixture.product

import com.kakaoent.md.domain.mall.MallProduct
import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.support.Period
import java.time.Instant

fun product(
    productId: String,
    price: Long = 0,
    salesPeriod: Period = Period.ALWAYS,
    displayedAt: Instant = Instant.EPOCH,
    status: Product.Status = Product.Status.ON_SALE,
): Product = Product(
    productId = productId,
    price = price,
    salesPeriod = salesPeriod,
    displayedAt = displayedAt,
    status = status
)

fun mallProduct(
    mallId: String,
    productId: String,
): MallProduct = MallProduct(
    mallId = mallId,
    productId = productId
)
