package com.kakaoent.md.application.api.customerservice

data class FAQCategoriesResponse(
    val categories: List<FAQCategory>
)

data class FAQCategory(
    val categoryId: String,
    val categoryName: String
)

data class FAQResponse(
    val faqs: List<FAQ>,
)

data class FAQ(
    val question: String,
    val answer: String
)
