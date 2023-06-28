package com.kakaoent.md.application.api.codecategory

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.codecategory.CodeCategoryController.Companion.GET_CODE_CATEGORY
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
import com.kakaoent.md.docs.responseBody
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType.ARRAY
import org.springframework.restdocs.payload.JsonFieldType.STRING

@WebMvcTest(controllers = [CodeCategoryController::class])
class CodeCategoryApiSpec : ApiSpec() {

    @MockkBean
    lateinit var codeCategoryController: CodeCategoryController

    init {

        test("코드 카테고리 조회") {

            every { codeCategoryController.getCodeCategory(any()) } returns CodeCategoryResponse(
                name = "name",
                description = "description",
                codes = listOf(
                    CodeLine(
                        name = "name",
                        description = "description",
                    )
                )
            )

            mockMvc.perform(
                get(GET_CODE_CATEGORY, CODE_CATEGORY_NAME)
            ).andDocument(
                "CodeCategoryApiSpec 코드 카테고리 조회",
                pathVariables {
                    CODE_CATEGORY_NAME means "코드 카테고리 이름"
                },
                responseBody {
                    "name" type STRING means "코드 카테고리 이름"
                    "description" type STRING means "코드 카테고리 설명"
                    "codes" type ARRAY means "코드 목록"
                    "codes[].name" type STRING means "코드 이름"
                    "codes[].description" type STRING means "코드 설명"
                }
            )
        }
    }

    companion object {
        const val CODE_CATEGORY_NAME = "categoryName"
    }
}
