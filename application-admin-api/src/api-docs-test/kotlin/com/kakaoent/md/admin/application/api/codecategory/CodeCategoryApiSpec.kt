package com.kakaoent.md.admin.application.api.codecategory

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.commoncode.CodeCategoryController
import com.kakaoent.md.admin.application.api.commoncode.CodeCategoryController.Companion.DELETE_CODE_CATEGORY
import com.kakaoent.md.admin.application.api.commoncode.CodeCategoryController.Companion.REGISTER_CODE
import com.kakaoent.md.admin.application.api.commoncode.CodeCategoryController.Companion.REGISTER_CODE_CATEGORY
import com.kakaoent.md.admin.application.api.commoncode.CodeCategoryController.Companion.UPDATE_CODE_CATEGORY
import com.kakaoent.md.admin.application.api.commoncode.RegisterCodeCategoryRequest
import com.kakaoent.md.admin.application.api.commoncode.UpdateCodeCategoryRequest
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
import com.kakaoent.md.docs.requestBody
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.STRING

@WebMvcTest(controllers = [CodeCategoryController::class])
class CodeCategoryApiSpec: ApiSpec() {

    @MockkBean
    lateinit var codeCategoryController: CodeCategoryController

    init {

        test("코드 카테고리 등록") {

            every { codeCategoryController.registerCodeCategory(any()) } just Runs

            val request = RegisterCodeCategoryRequest(
                name = "name",
                description = "description",
            )

            mockMvc.perform(
                post(REGISTER_CODE_CATEGORY)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CodeCategoryApiSpec 코드 카테고리 등록",
                requestBody {
                    "name" type STRING means "코드 카테고리 이름"
                    "description" type STRING means "코드 카테고리 설명"
                }
            )
        }

        test("코드 카테고리 수정") {
            val request = UpdateCodeCategoryRequest(
                name = "name",
                description = "description",
            )

            every { codeCategoryController.updateCodeCategory(any()) } just Runs

            mockMvc.perform(
                patch(UPDATE_CODE_CATEGORY)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CodeCategoryApiSpec 코드 카테고리 수정",
                requestBody {
                    "name" type STRING means "코드 카테고리 이름"
                    "description" type STRING means "코드 카테고리 설명"
                }
            )
        }

        test("코드 카테고리 삭제") {

            every { codeCategoryController.deleteCodeCategory(any()) } just Runs

            mockMvc.perform(
                delete(DELETE_CODE_CATEGORY, CODE_CATEGORY_NAME)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "CodeCategoryApiSpec 코드 카테고리 삭제",
                pathVariables {
                    CODE_CATEGORY_NAME means "코드 카테고리 이름"
                }
            )
        }

        test("코드 등록") {

            every { codeCategoryController.addCode(any(), any()) } just Runs

            val request = RegisterCodeCategoryRequest(
                name = "name",
                description = "description",
            )

            mockMvc.perform(
                post(REGISTER_CODE, CODE_CATEGORY_NAME)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CodeCategoryApiSpec 코드 등록",
                pathVariables {
                    CODE_CATEGORY_NAME means "코드 카테고리 이름"
                },
                requestBody {
                    "name" type STRING means "코드 이름"
                    "description" type STRING means "코드 설명"
                }
            )
        }

        test("코드 수정") {

            every { codeCategoryController.updateCode(any(), any()) } just Runs

            val request = UpdateCodeCategoryRequest(
                name = "name",
                description = "description",
            )

            mockMvc.perform(
                patch(REGISTER_CODE, CODE_CATEGORY_NAME)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CodeCategoryApiSpec 코드 수정",
                pathVariables {
                    CODE_CATEGORY_NAME means "코드 카테고리 이름"
                },
                requestBody {
                    "name" type STRING means "코드 이름"
                    "description" type STRING means "코드 설명"
                }
            )
        }

        test("코드 삭제") {

            every { codeCategoryController.deleteCode(any(), any()) } just Runs

            mockMvc.perform(
                delete(REGISTER_CODE, CODE_CATEGORY_NAME)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "CodeCategoryApiSpec 코드 삭제",
                pathVariables {
                    CODE_CATEGORY_NAME means "코드 카테고리 이름"
                }
            )
        }
    }

    companion object {
        private const val CODE_CATEGORY_NAME = "categoryName"
        private const val CODE_NAME = "codeName"
    }
}
