package com.kakaoent.md.admin.application.api.commoncode

import org.springframework.web.bind.annotation.*

@RestController
class CodeCategoryController(
    private val codeCategoryService: CodeCategoryService
) {

    @PostMapping(REGISTER_CODE_CATEGORY)
    fun registerCodeCategory(
        @RequestBody request: RegisterCodeCategoryRequest
    ) {
        codeCategoryService.register(request)
    }

    @PatchMapping(UPDATE_CODE_CATEGORY)
    fun updateCodeCategory(
        @RequestBody request: UpdateCodeCategoryRequest
    ) {
        codeCategoryService.update(request)
    }

    @DeleteMapping(DELETE_CODE_CATEGORY)
    fun deleteCodeCategory(
        @PathVariable categoryName: String
    ) {
        codeCategoryService.delete(categoryName)
    }

    @PostMapping(REGISTER_CODE)
    fun addCode(
        @PathVariable categoryName: String,
        @RequestBody request: AddCodeRequest
    ) {
        codeCategoryService.addCode(categoryName, request)
    }

    @PatchMapping(UPDATE_CODE)
    fun updateCode(
        @PathVariable categoryName: String,
        @RequestBody request: UpdateCodeRequest,
    ) {
        codeCategoryService.updateCode(categoryName, request)
    }

    @DeleteMapping(DELETE_CODE)
    fun deleteCode(
        @PathVariable categoryName: String,
        @PathVariable codeName: String
    ) {
        codeCategoryService.deleteCode(categoryName, codeName)
    }

    companion object {
        const val REGISTER_CODE_CATEGORY = "/code-categories"
        const val UPDATE_CODE_CATEGORY = "/code-categories"
        const val DELETE_CODE_CATEGORY = "/code-categories/{categoryName}"
        const val REGISTER_CODE = "/code-categories/{categoryName}/codes"
        const val UPDATE_CODE = "/code-categories/{categoryName}/codes"
        const val DELETE_CODE = "/code-categories/{categoryName}/codes/{codeName}"
    }
}
