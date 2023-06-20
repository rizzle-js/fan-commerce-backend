package com.kakaoent.md.admin.application.api.customerservice

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.customerservice.CustomerServiceAdminController.Companion.UPDATE_INQUIRY_CATEGORY
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put
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
                    "categoryName" type STRING means "카테고리명"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }
    }
}