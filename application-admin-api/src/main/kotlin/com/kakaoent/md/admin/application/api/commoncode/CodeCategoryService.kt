package com.kakaoent.md.admin.application.api.commoncode

import com.kakaoent.md.config.CODE_CATEGORIES_CACHE
import com.kakaoent.md.domain.code.Code
import com.kakaoent.md.domain.code.CodeCategory
import com.kakaoent.md.domain.code.CodeCategoryRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CodeCategoryService(
    private val codeCategoryRepository: CodeCategoryRepository
) {

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0.name")
    fun register(request: RegisterCodeCategoryRequest) {
        val codeCategory = codeCategoryRepository.findByName(request.name)

        if (codeCategory != null) {
            throw IllegalArgumentException("이미 존재하는 코드 카테고리입니다.")
        }

        codeCategoryRepository.save(
            CodeCategory(
                name = request.name,
                description = request.description
            )
        )
    }

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0.name")
    fun update(request: UpdateCodeCategoryRequest) {
        val codeCategory = codeCategoryRepository.getByName(request.name)

        codeCategory.update(
            name = request.name,
            description = request.description
        )
    }

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0")
    fun delete(codeCategoryName: String) {
        val codeCategory = codeCategoryRepository.getByName(codeCategoryName)

        codeCategory.delete()
    }

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0")
    fun addCode(codeCategoryName: String, addCodeRequest: AddCodeRequest) {
        val codeCategory = codeCategoryRepository.getByName(codeCategoryName)

        val code = Code(
            codeCategory = codeCategory,
            name = addCodeRequest.name,
            description = addCodeRequest.description
        )

        codeCategory.addCode(code)
    }

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0")
    fun updateCode(codeCategoryName: String, updateCodeRequest: UpdateCodeRequest) {
        val codeCategory = codeCategoryRepository.getByName(codeCategoryName)
        codeCategory.updateCode(
            codeName = updateCodeRequest.name,
            codeDescription = updateCodeRequest.description
        )
    }

    @Transactional
    @CacheEvict(value = [CODE_CATEGORIES_CACHE], key = "#p0")
    fun deleteCode(codeCategoryName: String, codeName: String) {
        val codeCategory = codeCategoryRepository.getByName(codeCategoryName)
        codeCategory.deleteCode(codeName)
    }
}
