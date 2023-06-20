package com.kakaoent.md.admin.application.api.benefit

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.DEACTIVATE_BENEFIT
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.REGISTER_BENEFIT
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.UPDATE_BENEFIT
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*
import java.time.Instant

@WebMvcTest(controllers = [BenefitAdminController::class])
class BenefitAdminApiSpec : ApiSpec() {
    init {
        test("혜택을 등록하다") {
            val request = RegisterBenefitRequest(
                benefitName = "New Year Discount",
                benefitContent = "10% off for all items",
                benefitType = BenefitType.DISCOUNT,
                applyRange = ApplyRange.ALL,
                benefitStatus = BenefitStatus.ACTIVE,
                startAt = Instant.ofEpochSecond(1686641320L),
                endAt = Instant.ofEpochSecond(1686644320L)
            )
            mockMvc.perform(
                post(REGISTER_BENEFIT)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "BenefitAdminApiSpec 혜택 등록",
                requestBody {
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "benefitStatus" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "benefitStatus" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                }
            )
        }

        test("혜택 정보를 수정하다") {
            val request = UpdateBenefitRequest(
                benefitName = "New Year Discount",
                benefitContent = "10% off for all items",
                benefitType = BenefitType.DISCOUNT,
                applyRange = ApplyRange.ALL,
                benefitStatus = BenefitStatus.ACTIVE,
                startAt = Instant.ofEpochSecond(1686641320L),
                endAt = Instant.ofEpochSecond(1686644320L)
            )
            mockMvc.perform(
                put(UPDATE_BENEFIT, BENEFIT_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "BenefitAdminApiSpec 혜택 수정",
                pathVariables {
                    "benefitId" means "혜택 ID"
                },
                requestBody {
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "benefitStatus" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "benefitStatus" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                    "updateAt" type NUMBER means "수정 날짜"
                }
            )
        }

        test("혜택을 비활성화하다") {
            mockMvc.perform(
                patch(DEACTIVATE_BENEFIT, BENEFIT_UUID)
                    .contentType(APPLICATION_JSON)
            ).andDocument(
                "BenefitAdminApiSpec 혜택 비활성화",
                pathVariables {
                    "benefitId" means "혜택ID"
                },
                responseBody {
                    "benefitId" type STRING means "혜택ID"
                    "benefitStatus" type STRING means "혜택 상태(비활성화)"
                    "updateAt" type NUMBER means "수정 날짜"
                }
            )
        }
    }

}