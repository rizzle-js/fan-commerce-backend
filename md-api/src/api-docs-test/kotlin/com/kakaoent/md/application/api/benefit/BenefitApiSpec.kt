package com.kakaoent.md.application.api.benefit

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.andDocument
import com.kakaoent.md.application.api.benefit.BenefitController.Companion.GET_BENEFIT
import com.kakaoent.md.application.api.requestBody
import com.kakaoent.md.application.api.responseBody
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType.NUMBER
import org.springframework.restdocs.payload.JsonFieldType.STRING

@WebMvcTest(controllers = [BenefitController::class])
class BenefitApiSpec : ApiSpec() {
    init {
        test("혜택 조회") {
            val request = BenefitRequest(
                memberKey = 1,
            )

            mockMvc.perform(
                get(GET_BENEFIT)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "BenefitApiSpec 혜택 조회",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "benefitDetails.description" type STRING means "혜택 설명"
                    "benefitDetails.condition" type STRING means "혜택 조건"
                    "validPeriod.startAt" type NUMBER means "유효 기간 시작"
                    "validPeriod.endAt" type NUMBER means "유효 기간 종료"
                }
            )
        }
    }
}