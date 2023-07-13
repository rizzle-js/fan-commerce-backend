package com.kakaoent.md.fixture.product

import com.kakaoent.md.domain.product.ProductOption
import com.kakaoent.md.domain.product.SalesProduct

fun salesProduct(
    productId: String,
    salesProductId: String,
    quantity: Long = 10,
    status: SalesProduct.SalesStatus = SalesProduct.SalesStatus.ON_SALE,
    productOptions: List<ProductOption> = emptyList()
): SalesProduct = SalesProduct(
    productId = productId,
    salesProductId = salesProductId,
    quantity = quantity,
    salesStatus = status,
).apply {
    productOptions.onEach {
        addSalesProductOption(it)
    }
}
