package com.kakaoent.md.domain.code

interface CodeCategoryRepository {

    fun save(codeCategory: CodeCategory): CodeCategory

    fun getByCodeCategoryId(codeCategoryId: String): CodeCategory

    fun findByCodeCategoryId(codeCategoryId: String, deleted: Boolean = false): CodeCategory?

    fun findAll(): List<CodeCategory>

}
