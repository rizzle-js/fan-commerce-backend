package com.kakaoent.md.admin.application.api.customerservice

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.ANSWER_INQUIRY
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.DELETE_INQUIRY_CATEGORY
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.REGISTER_INQUIRY_CATEGORY
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.UPDATE_INQUIRY_ANSWER
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.UPDATE_INQUIRY_CATEGORY
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.NUMBER
import org.springframework.restdocs.payload.JsonFieldType.STRING

@WebMvcTest(controllers = [CustomerServiceAdminController::class])
class CustomerServiceAdminApiSpec : ApiSpec() {
    init {
        test("1:1 문의 카테고리를 수정하다") {
            mockMvc.perform(
                put(UPDATE_INQUIRY_CATEGORY, INQUIRY_CATEGORY_UUID).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            UpdateInquiryCategoryRequest(categoryName = "새로운 카테고리")
                        )
                    )
            ).andDocument(
                "CustomerServiceAdminApiSpec 1:1 문의 카테고리 수정",
                requestBody {
                    "categoryName" type STRING means "카테고리명"
                },
                responseBody {
                    "categoryId" type STRING means "카테고리 ID"
                }
            )
        }

        test("1:1 문의 카테고리를 등록하다") {
            mockMvc.perform(
                post(REGISTER_INQUIRY_CATEGORY).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            RegisterInquiryCategoryRequest(
                                categoryName = "새로운 카테고리"
                            )
                        )
                    )
            ).andDocument(
                "CustomerServiceAdminApiSpec 1:1 문의 카테고리 등록",
                requestBody {
                    "categoryName" type STRING means "카테고리명"
                },
                responseBody {
                    "categoryId" type STRING means "카테고리 ID"
                    "categoryName" type STRING means "카테고리명"
                }
            )
        }

        test("1:1 문의 카테고리를 삭제하다") {
            mockMvc.perform(
                delete(DELETE_INQUIRY_CATEGORY, INQUIRY_CATEGORY_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "CustomerServiceAdminApiSpec 1:1 문의 카테고리 삭제",
                pathVariables {
                    "categoryId" means "카테고리 ID"
                },
                responseBody {
                    "categoryId" type STRING means "카테고리 ID"
                }
            )
        }

        test("1:1 문의에 답변하다") {
            mockMvc.perform(
                post(ANSWER_INQUIRY, INQUIRY_UUID).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            AnswerInquiryRequest(
                                answerContent = "testAnswerContent"
                            )
                        )
                    )
            ).andDocument(
                "CustomerServiceAdminApiSpec 1:1 문의 답변",
                pathVariables {
                    "inquiryId" means "문의 ID"
                },
                requestBody {
                    "answerContent" type STRING means "답변 내용"
                },
                responseBody {
                    "inquiryId" type STRING means "문의 ID"
                    "answerId" type STRING means "답변 ID"
                    "answerContent" type STRING means "답변 내용"
                    "answeredAt" type NUMBER means "답변 날짜"
                }
            )
        }

        test("1:1 문의 답변을 수정하다") {
            mockMvc.perform(
                put(UPDATE_INQUIRY_ANSWER, INQUIRY_UUID, INQUIRY_ANSERT_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "CustomerServiceAdminApiSpec 1:1 문의 답변 수정",
                pathVariables {
                    "inquiryId" means "문의 ID"
                    "answerId" means "답변 ID"
                },
                responseBody {
                    "inquiryId" type STRING means "문의 ID"
                    "answerId" type STRING means "답변 ID"
                }
            )
        }
    }
}