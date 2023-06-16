package com.kakaoent.md.application.api.benefit

import java.time.Instant

data class BenefitResponse(
    val memberKey: Long,
    val benefitId: String,
    val benefitName: String,
    val benefitDetails: BenefitDetails,
    val validPeriod: ValidPeriod
)

data class BenefitDetails(
    val description: String,
    val condition: String
)

data class ValidPeriod(
    val startAt: Instant,
    val endAt: Instant
)

data class BenefitApplyResponse(
    val memberKey: Long,
    val productId: String,
    val productName: String,
    val benefitId: String,
    val benefitName: String,
    val appliedAt: Instant
)
