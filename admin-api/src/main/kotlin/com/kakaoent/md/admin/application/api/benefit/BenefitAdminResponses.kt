package com.kakaoent.md.admin.application.api.benefit

import java.time.Instant

data class RegisterBenefitResponse(
    val benefitId: String,
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val benefitStatus: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant
)

data class UpdateBenefitResponse(
    val benefitId: String,
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val benefitStatus: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant,
    val updatedAt: Instant
)

data class DeactivateBenefitResponse(
    val benefitId: String,
    val benefitStatus: BenefitStatus,
    val updatedAt: Instant
)

data class DeleteBenefitResponse(
    val benefitId: String,
)
