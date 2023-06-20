package com.kakaoent.md.admin.application.api.benefit

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.DEACTIVATE_BENEFIT
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.DELETE_BENEFIT
import com.kakaoent.md.admin.application.api.benefit.BenefitAdminController.Companion.GET_BENEFITS
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
        test("혜택 목록을 조회하다") {
            mockMvc.perform(
                get(GET_BENEFITS).contentType(APPLICATION_JSON)
            ).andDocument(
                "BenefitAdminApiSpec 혜택 목록 조회",
                responseBody {
                    "benefits[]" type ARRAY means "혜택 목록"
                    "benefits[].benefitId" type STRING means "혜택 ID"
                    "benefits[].benefitName" type STRING means "혜택명"
                    "benefits[].applyRange" type STRING means "적용 범위"
                    "benefits[].status" type STRING means "상태"
                    "benefits[].startAt" type NUMBER means "시작 날짜"
                    "benefits[].endAt" type NUMBER means "종료 날짜"
                }
            )
        }

        test("혜택을 등록하다") {
            val request = RegisterBenefitRequest(
                benefitName = "New Year Discount",
                benefitContent = "10% off for all items",
                benefitType = BenefitType.DISCOUNT,
                applyRange = ApplyRange.ALL,
                status = BenefitStatus.ACTIVE,
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
                    "status" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "status" type STRING means "혜택 상태(비활성화)"
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
                status = BenefitStatus.ACTIVE,
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
                    "status" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                    "benefitName" type STRING means "혜택명"
                    "benefitContent" type STRING means "혜택 내용"
                    "benefitType" type STRING means "혜택 유형"
                    "applyRange" type STRING means "적용 범위"
                    "status" type STRING means "혜택 상태(비활성화)"
                    "startAt" type NUMBER means "시작 날짜"
                    "endAt" type NUMBER means "종료 날짜"
                    "updatedAt" type NUMBER means "수정 날짜"
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
                    "benefitId" means "혜택 ID"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                    "status" type STRING means "혜택 상태(비활성화)"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }

        test("혜택을 삭제하다") {
            mockMvc.perform(
                delete(DELETE_BENEFIT, BENEFIT_UUID)
                    .contentType(APPLICATION_JSON)
            ).andDocument(
                "BenefitAdminApiSpec 혜택 삭제",
                pathVariables {
                    "benefitId" means "혜택 ID"
                },
                responseBody {
                    "benefitId" type STRING means "혜택 ID"
                }
            )
        }
    }

}