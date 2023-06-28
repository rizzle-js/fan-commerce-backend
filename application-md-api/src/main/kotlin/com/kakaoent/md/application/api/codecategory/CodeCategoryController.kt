package com.kakaoent.md.application.api.codecategory

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CodeCategoryController(
    private val codeCategoryService: CodeCategoryService
) {

    @GetMapping(GET_CODE_CATEGORY)
    fun getCodeCategory(
        @PathVariable categoryName: String
    ): CodeCategoryResponse {
        return codeCategoryService.getCodeCategory(categoryName)
    }

    companion object {
        const val GET_CODE_CATEGORY = "/code-categories/{categoryName}"
    }
}
