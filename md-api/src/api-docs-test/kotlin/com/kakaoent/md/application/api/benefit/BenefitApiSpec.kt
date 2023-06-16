package com.kakaoent.md.application.api.benefit

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.benefit.BenefitController.Companion.APPLY_BENEFIT
import com.kakaoent.md.application.api.benefit.BenefitController.Companion.GET_BENEFIT
import com.kakaoent.md.application.api.product.PRODUCT_UUID
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
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
                get(GET_BENEFIT, BENEFIT_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "BenefitApiSpec 혜택 조회",
                pathVariables {
                    "benefitId" means "혜택 ID"
                },
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

        test("혜택 적용") {
            val request = BenefitApplyRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                productName = "응원봉(상품명)",
            )

            mockMvc.perform(
                post(APPLY_BENEFIT, BENEFIT_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "BenefitApiSpec 혜택 적용",
                pathVariables {
                    "benefitId" means "혜택 ID"
                },
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "productName" type STRING means "상품명"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "productName" type STRING means "상품명"
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "appliedAt" type NUMBER means "적용 날짜"
                }
            )
        }
    }
}