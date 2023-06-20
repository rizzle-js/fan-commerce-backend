package com.kakaoent.md.admin.application.api.benefit

import java.time.Instant

data class RegisterBenefitRequest(
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val benefitStatus: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant
)

enum class BenefitType {
    DISCOUNT, FREE_SHIPPING, FREE_GIFT
}

enum class ApplyRange {
    ALL, CHANNEL, CHANNEL_GROUP
}

enum class BenefitStatus {
    ACTIVE, INACTIVE
}

data class UpdateBenefitRequest(
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val benefitStatus: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant
)
