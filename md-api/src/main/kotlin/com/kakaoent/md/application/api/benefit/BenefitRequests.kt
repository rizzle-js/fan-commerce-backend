package com.kakaoent.md.application.api.benefit

data class BenefitRequest(
    val memberKey: Long,
)

data class BenefitApplyRequest(
    val memberKey: Long,
    val productId: String,
    val productName: String,
)
