package com.kakaoent.md.admin.application.api.customerservice

import java.time.Instant

data class UpdateInquiryCategoryResponse(
    val categoryId: String,
    val categoryName: String,
    val updatedAt: Instant
)

data class RegisterInquiryCategoryResponse(
    val categoryId: String,
    val categoryName: String,
    val addedAt: Instant
)
