package com.kakaoent.md.admin.application.api.benefit

import java.time.Instant

data class RegisterBenefitResponse(
    val benefitId: String,
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val status: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant
)

data class UpdateBenefitResponse(
    val benefitId: String,
    val benefitName: String,
    val benefitContent: String,
    val benefitType: BenefitType,
    val applyRange: ApplyRange,
    val status: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant,
)

data class DeactivateBenefitResponse(
    val benefitId: String,
    val status: BenefitStatus,
)

data class DeleteBenefitResponse(
    val benefitId: String,
)

data class BenefitsResponse(
    val benefits: List<Benefit>
)

data class Benefit(
    val benefitId: String,
    val benefitName: String,
    val applyRange: ApplyRange,
    val status: BenefitStatus,
    val startAt: Instant,
    val endAt: Instant,
)
