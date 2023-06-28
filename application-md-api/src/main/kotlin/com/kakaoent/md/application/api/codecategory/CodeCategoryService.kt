package com.kakaoent.md.application.api.codecategory

import com.kakaoent.md.config.CODE_CATEGORIES_CACHE
import com.kakaoent.md.domain.code.CodeCategoryRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CodeCategoryService(
    private val codeCategoryRepository: CodeCategoryRepository
) {

    @Caching(
        cacheable = [Cacheable(value = [CODE_CATEGORIES_CACHE], key = "#p0")]
    )
    fun getCodeCategory(categoryName: String): CodeCategoryResponse {
        val codeCategory = codeCategoryRepository.getByName(categoryName)

        return CodeCategoryResponse(
            name = codeCategory.name,
            description = codeCategory.description,
            codes = codeCategory.codes.map {
                CodeLine(
                    name = it.name,
                    description = it.description
                )
            }
        )
    }
}
