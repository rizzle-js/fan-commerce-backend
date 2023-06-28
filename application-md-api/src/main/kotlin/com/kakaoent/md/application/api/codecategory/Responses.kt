package com.kakaoent.md.application.api.codecategory

data class CodeCategoriesResponse(
    val codeCategories: List<CodeCategoryResponse>
)

data class CodeCategoryResponse(
    val name: String,
    val description: String,
    val codes: List<CodeLine>
)

data class CodeLine(
    val name: String,
    val description: String
)
