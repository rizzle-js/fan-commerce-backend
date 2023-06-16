package com.kakaoent.md.application.api.customerservice

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class CustomerServiceController {
    @GetMapping(GET_FAQ_CATEGORIES)
    fun getFAQCategories(): FAQCategoriesResponse {
        return FAQCategoriesResponse(
            listOf(
                FAQCategory(
                    categoryId = "1",
                    categoryName = "결제"
                ),
                FAQCategory(
                    categoryId = "2",
                    categoryName = "배송"
                ),
                FAQCategory(
                    categoryId = "3",
                    categoryName = "환불"
                )
            )
        )
    }

    companion object {
        const val GET_FAQ_CATEGORIES = "/faq/categories"
    }
}