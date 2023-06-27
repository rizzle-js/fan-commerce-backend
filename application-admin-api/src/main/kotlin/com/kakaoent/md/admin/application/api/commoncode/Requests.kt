package com.kakaoent.md.admin.application.api.commoncode

data class RegisterCodeCategoryRequest(
    val name: String,
    val description: String,
)

data class UpdateCodeCategoryRequest(
    val name: String,
    val description: String,
)

data class AddCodeRequest(
    val name: String,
    val description: String,
)

data class UpdateCodeRequest(
    val name: String,
    val description: String,
)
