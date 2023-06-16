package com.kakaoent.md.application.api.customerservice

import org.springframework.web.bind.annotation.*

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


    @GetMapping(GET_FAQ)
    fun getFAQ(
        @PathVariable categoryId: String,
    ): FAQResponse {
        return FAQResponse(
            faqs = listOf(
                FAQ(
                    question = "질문1",
                    answer = "답변1"
                ),
                FAQ(
                    question = "질문2",
                    answer = "답변2"
                )
            ),
        )
    }

    companion object {
        const val GET_FAQ_CATEGORIES = "/faq/categories"
        const val GET_FAQ = "/faq/categories/{categoryId}"
    }
}