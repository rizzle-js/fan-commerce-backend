package com.kakaoent.md.application.api.benefit

import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.time.temporal.ChronoUnit.DAYS

@RestController
class BenefitController {
    @GetMapping(GET_BENEFIT)
    fun getBenefit(
        @RequestBody request: BenefitRequest
    ): BenefitResponse {
        // Logic to call the service and handle exceptions
        return BenefitResponse(
            memberKey = request.memberKey,
            benefitId = "42Qaq1EMJ7oCn9swSsoh2J",
            benefitName = "혜택명", // this should be fetched from a service
            benefitDetails = BenefitDetails(
                description = "혜택 설명", // this should be fetched from a service
                condition = "혜택 조건" // this should be fetched from a service
            ),
            validPeriod = ValidPeriod(
                startAt = Instant.ofEpochSecond(1686641320L),
                endAt = Instant.ofEpochSecond(1686641320L).plus(30, DAYS)
            )
        )
    }

    companion object {
        const val GET_BENEFIT = "/benefit"
    }
}