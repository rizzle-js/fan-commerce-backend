package com.kakaoent.md.application.api.customerservice

data class FAQCategoriesResponse(
    val categories: List<FAQCategory>
)

data class FAQCategory(
    val categoryId: String,
    val categoryName: String
)
