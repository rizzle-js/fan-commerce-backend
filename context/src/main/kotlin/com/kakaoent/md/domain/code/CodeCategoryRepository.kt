package com.kakaoent.md.domain.code

interface CodeCategoryRepository {

    fun save(codeCategory: CodeCategory): CodeCategory

    fun delete(codeCategory: CodeCategory)

    fun getByName(name: String): CodeCategory

    fun findByName(name: String): CodeCategory?

    fun findAll(): List<CodeCategory>

}
