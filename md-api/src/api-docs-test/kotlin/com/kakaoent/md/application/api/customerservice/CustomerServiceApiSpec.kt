package com.kakaoent.md.application.api.customerservice

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.customerservice.CustomerServiceController.Companion.GET_FAQ
import com.kakaoent.md.application.api.customerservice.CustomerServiceController.Companion.GET_FAQ_CATEGORIES
import com.kakaoent.md.application.api.customerservice.CustomerServiceController.Companion.GET_INQUIRY_CATEGORIES
import com.kakaoent.md.application.api.customerservice.CustomerServiceController.Companion.GET_INQUIRY_LIST
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [CustomerServiceController::class])
class CustomerServiceApiSpec : ApiSpec() {
    init {
        test("FAQ 카테고리 조회") {
            mockMvc.perform(
                get(GET_FAQ_CATEGORIES).contentType(APPLICATION_JSON)
            ).andDocument(
                "CustomerServiceApiSpec FAQ 카테고리 조회",
                responseBody {
                    "categories[]" type ARRAY means "카테고리 목록"
                    "categories[].categoryId" type STRING means "카테고리 ID"
                    "categories[].categoryName" type STRING means "카테고리 이름"
                }
            )
        }

        test("FAQ 조회") {
            mockMvc.perform(
                get(GET_FAQ, CATEGORY_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "CustomerServiceApiSpec FAQ 조회",
                pathVariables {
                    "categoryId" means "카테고리 ID"
                },
                responseBody {
                    "faqs[]" type ARRAY means "FAQ 리스트"
                    "faqs[].question" type STRING means "질문"
                    "faqs[].answer" type STRING means "답변"
                }
            )
        }

        test("1:1 문의 카테고리를 조회하다") {
            mockMvc.perform(
                get(GET_INQUIRY_CATEGORIES).contentType(APPLICATION_JSON)
            ).andDocument(
                "CustomerServiceApiSpec 1:1 문의 카테고리 조회",
                responseBody {
                    "categories[]" type ARRAY means "카테고리 목록"
                    "categories[].categoryId" type NUMBER means "카테고리 ID"
                    "categories[].categoryName" type STRING means "카테고리 이름"
                }
            )
        }


        test("1:1 문의 리스트를 조회하다") {
            mockMvc.perform(
                get(GET_INQUIRY_LIST).contentType(APPLICATION_JSON)
                    .param("memberKey", "1")
            ).andDocument(
                "CustomerServiceApiSpec 1:1 문의 리스트 조회",
                queryParams {
                    "memberKey" means "회원 키"
                },
                responseBody {
                    "inquiries[]" type ARRAY means "문의 목록"
                    "inquiries[].inquiryId" type NUMBER means "문의 ID"
                    "inquiries[].category" type STRING means "카테고리"
                    "inquiries[].content" type STRING means "문의 내용"
                    "inquiries[].status" type STRING means "문의 상태"
                    "inquiries[].inquiryAt" type NUMBER means "문의 일시"
                }
            )
        }
    }
}