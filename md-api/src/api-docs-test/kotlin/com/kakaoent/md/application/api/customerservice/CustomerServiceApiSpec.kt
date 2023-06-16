package com.kakaoent.md.application.api.customerservice

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.andDocument
import com.kakaoent.md.application.api.customerservice.CustomerServiceController.Companion.GET_FAQ_CATEGORIES
import com.kakaoent.md.application.api.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType.ARRAY
import org.springframework.restdocs.payload.JsonFieldType.STRING

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
    }
}