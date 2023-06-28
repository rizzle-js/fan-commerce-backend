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
                    categoryId = "2mJpvTlF2Drwf3GjObfUMl",
                    categoryName = "결제"
                ),
                FAQCategory(
                    categoryId = "2MjPvTlF2Drwf3GjObfUMl",
                    categoryName = "배송"
                ),
                FAQCategory(
                    categoryId = "KMjpVtlf2Drwf3GjObfUMl",
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
                    faqId = "181CZHimCdUT1IbYfLC8RU",
                    question = "질문1",
                    answer = "답변1"
                ),
                FAQ(
                    faqId = "1HcbOmuCAV9ceKziXZ1oxa",
                    question = "질문2",
                    answer = "답변2"
                )
            ),
        )
    }

    @GetMapping(GET_INQUIRY_CATEGORIES)
    fun getInquiryCategories(): InquiryCategoriesResponse {
        return InquiryCategoriesResponse(
            categories = listOf(
                InquiryCategory(
                    categoryId = "4y8BJSYmT7j4b4OEkxsfRO",
                    categoryName = "결제 관련"
                ),
                InquiryCategory(
                    categoryId = "0FCLHPt83DkQsM4QgYb6P2",
                    categoryName = "서비스 이용 관련"
                )
            ),
        )
    }

    @GetMapping(GET_INQUIRY_LIST)
    fun getInquiryList(
        @RequestParam memberKey: Long
    ): InquiryListResponse {
        return InquiryListResponse(
            inquiries = listOf(
                Inquiry(
                    inquiryId = "3n0lslC4qftIhBaG5xXJOW",
                    category = "결제 관련",
                    content = "결제가 안 됩니다.",
                    status = InquiryStatus.PENDING,
                    Instant.ofEpochSecond(1686641320L),
                ),
                Inquiry(
                    inquiryId = "3Mw6Htofzta5tLySwEFwmO",
                    category = "서비스 이용 관련",
                    content = "상품이 안 나와요.",
                    status = InquiryStatus.ANSWERED,
                    Instant.ofEpochSecond(1686641320L),
                )
            ),
        )
    }

    @PostMapping(SUBMIT_INQUIRY)
    fun submitInquiry(
        @RequestBody inquiryRequest: InquiryRequest
    ): InquiryResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return InquiryResponse(
            memberKey = inquiryRequest.memberKey,
            inquiryId = "2ZEPqEhRpFsRQcQgc739JG",
            category = inquiryRequest.category,
            content = inquiryRequest.content,
            inquiryAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    @GetMapping(GET_INQUIRY_RESULT)
    fun getInquiryResult(
        @PathVariable inquiryId: String,
    ): InquiryResultResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return InquiryResultResponse(
            inquiryId = inquiryId,
            status = InquiryStatus.ANSWERED,
            answerContent = "해당 문제는 ~~~ 방법으로 해결 가능합니다.",
            answerAt = Instant.ofEpochSecond(1686641320L),
        )
    }

    @PutMapping(CLOSE_INQUIRY)
    fun closeInquiry(
        @PathVariable inquiryId: String,
    ): CloseInquiryResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return CloseInquiryResponse(
            inquiryId = inquiryId,
            status = InquiryStatus.CLOSED,
            closeAt = Instant.ofEpochSecond(1686641320L),
        )
    }


    companion object {
        const val GET_FAQ_CATEGORIES = "/faq-categories"
        const val GET_FAQ = "/faq-categories/{categoryId}/faqs"
        const val GET_INQUIRY_CATEGORIES = "/inquiry-categories"
        const val GET_INQUIRY_LIST = "/inquiries"
        const val SUBMIT_INQUIRY = "/inquiries"
        const val GET_INQUIRY_RESULT = "/inquiries/{inquiryId}"
        const val CLOSE_INQUIRY = "/inquiries/{inquiryId}"
    }
}