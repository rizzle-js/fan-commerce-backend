package com.kakaoent.md.application.api.product

import com.kakaoent.md.domain.product.Displaying
import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.support.ascendingBy
import com.kakaoent.md.support.descendingBy
import org.springframework.data.domain.Sort
import org.springframework.data.mapping.div

enum class ProductSortOption(val sort: Sort) {
    RECENT(descendingBy(Product::displaying / Displaying::displayedAt)),
    PRICE_ASC(ascendingBy(Product::price)),
    PRICE_DESC(descendingBy(Product::price));
}
