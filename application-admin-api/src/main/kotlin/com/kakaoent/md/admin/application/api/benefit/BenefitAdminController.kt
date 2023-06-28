package com.kakaoent.md.admin.application.api.benefit

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class BenefitAdminController {

    @GetMapping(GET_BENEFITS)
    fun getBenefits(): BenefitsResponse {
        return BenefitsResponse(
            listOf(
                Benefit(
                    benefitId = "4cwmutn4e5qLSa6wmJP3qe",
                    benefitName = "혜택1",
                    applyRange = ApplyRange.ALL,
                    startAt = Instant.ofEpochSecond(1686641320L),
                    endAt = Instant.ofEpochSecond(1686644320L),
                    status = BenefitStatus.ACTIVE
                )
            )
        )
    }

    @PostMapping(REGISTER_BENEFIT)
    fun registerBenefit(
        @RequestBody request: RegisterBenefitRequest
    ): RegisterBenefitResponse {
        // 혜택을 등록하는 로직은 이곳에 작성
        // 여기서는 예시로 임의의 값을 리턴하도록 함
        return RegisterBenefitResponse(
            benefitId = "4cwmutn4e5qLSa6wmJP3qe",
            benefitName = request.benefitName,
            benefitContent = request.benefitContent,
            benefitType = request.benefitType,
            applyRange = request.applyRange,
            status = request.status,
            startAt = request.startAt,
            endAt = request.endAt
        )
    }

    @PutMapping(UPDATE_BENEFIT)
    fun updateBenefit(
        @PathVariable benefitId: String,
        @RequestBody request: UpdateBenefitRequest
    ): UpdateBenefitResponse {
        // 혜택을 수정하는 로직은 이곳에 작성
        // 여기서는 예시로 임의의 값을 리턴하도록 함
        return UpdateBenefitResponse(
            benefitId = benefitId,
            benefitName = request.benefitName,
            benefitContent = request.benefitContent,
            benefitType = request.benefitType,
            applyRange = request.applyRange,
            status = request.status,
            startAt = request.startAt,
            endAt = request.endAt,
        )
    }

    @PatchMapping(DEACTIVATE_BENEFIT)
    fun deactivateBenefit(
        @PathVariable benefitId: String
    ): DeactivateBenefitResponse {
        // 혜택을 비활성화하는 로직은 이곳에 작성
        // 여기서는 예시로 임의의 값을 리턴하도록 함
        return DeactivateBenefitResponse(
            benefitId = benefitId,
            status = BenefitStatus.INACTIVE,
        )
    }


    @DeleteMapping(DELETE_BENEFIT)
    fun deleteBenefit(
        @PathVariable benefitId: String
    ): DeleteBenefitResponse {
        // 혜택을 삭제하는 로직은 이곳에 작성
        // 여기서는 예시로 임의의 값을 리턴하도록 함
        return DeleteBenefitResponse(
            benefitId = benefitId,
        )
    }


    companion object {
        const val GET_BENEFITS = "/benefits"
        const val REGISTER_BENEFIT = "/benefits/register"
        const val UPDATE_BENEFIT = "/benefits/{benefitId}"
        const val DEACTIVATE_BENEFIT = "/benefits/{benefitId}/deactivate"
        const val DELETE_BENEFIT = "/benefits/{benefitId}"
    }
}