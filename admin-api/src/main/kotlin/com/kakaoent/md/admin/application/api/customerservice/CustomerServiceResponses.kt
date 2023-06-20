package com.kakaoent.md.admin.application.api.customerservice

import java.time.Instant

data class UpdateInquiryCategoryResponse(
    val categoryId: String,
    val categoryName: String,
    val updatedAt: Instant
)
