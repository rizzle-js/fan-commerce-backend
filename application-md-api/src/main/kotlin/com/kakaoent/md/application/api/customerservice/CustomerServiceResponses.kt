package com.kakaoent.md.application.api.customerservice

import java.time.Instant

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
    val faqId: String,
    val question: String,
    val answer: String
)

data class InquiryCategoriesResponse(
    val categories: List<InquiryCategory>,
)

data class InquiryCategory(
    val categoryId: String,
    val categoryName: String
)

data class InquiryListResponse(
    val inquiries: List<Inquiry>,
)

data class Inquiry(
    val inquiryId: String,
    val category: String,
    val content: String,
    val status: InquiryStatus,
    val inquiryAt: Instant
)

enum class InquiryStatus {
    PENDING,
    ANSWERED,
    CLOSED
}

data class InquiryResponse(
    val memberKey: Long,
    val inquiryId: String,
    val category: String,
    val content: String,
    val inquiryAt: Instant
)

data class InquiryResultResponse(
    val inquiryId: String,
    val status: InquiryStatus,
    val answerContent: String,
    val answerAt: Instant
)

data class CloseInquiryResponse(
    val inquiryId: String,
    val status: InquiryStatus,
    val closeAt: Instant
)