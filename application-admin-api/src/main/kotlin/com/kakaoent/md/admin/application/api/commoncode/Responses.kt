package com.kakaoent.md.admin.application.api.commoncode

data class CodeCategoriesResponse(
    val codeCategories: List<CodeCategoryResponse>
)

data class CodeCategoryResponse(
    val name: String,
    val description: String,
    val codes: List<CommonCodeLine>
)

data class CommonCodeLine(
    val name: String,
    val description: String
)
