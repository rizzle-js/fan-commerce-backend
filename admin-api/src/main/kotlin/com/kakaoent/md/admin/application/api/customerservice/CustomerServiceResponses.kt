package com.kakaoent.md.admin.application.api.customerservice

import java.time.Instant

data class UpdateInquiryCategoryResponse(
    val categoryId: String,
    val categoryName: String,
)

data class RegisterInquiryCategoryResponse(
    val categoryId: String,
    val categoryName: String,
)

data class DeleteInquiryCategoryResponse(
    val categoryId: String,
)

data class AnswerInquiryResponse(
    val inquiryId: String,
    val answerId: String,
    val answerContent: String,
    val answeredAt: Instant
)
