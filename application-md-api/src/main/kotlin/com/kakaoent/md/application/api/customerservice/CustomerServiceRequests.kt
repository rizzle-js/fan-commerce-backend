package com.kakaoent.md.application.api.customerservice

data class InquiryRequest(
    val memberKey: Long,
    val category: String,
    val content: String
)
