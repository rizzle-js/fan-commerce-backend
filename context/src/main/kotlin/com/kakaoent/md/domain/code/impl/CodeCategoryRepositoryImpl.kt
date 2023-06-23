package com.kakaoent.md.domain.code.impl

import com.kakaoent.md.domain.BaseRepository
import com.kakaoent.md.domain.code.CodeCategory
import com.kakaoent.md.domain.code.CodeCategoryRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

internal interface CodeCategoryCrudRepository : BaseRepository<CodeCategory, Long> {

    @Query("SELECT c FROM CodeCategory c WHERE c.codeCategoryId = :codeCategoryId AND c.deleted = :deleted")
    fun findByCodeCategoryId(codeCategoryId: String, deleted: Boolean = false): Optional<CodeCategory>

    @Query("SELECT DISTINCT c FROM CodeCategory c join fetch c._codes WHERE c.deleted = false")
    override fun findAll(): List<CodeCategory>
}

@Repository
internal class CodeCategoryRepositoryImpl(
    private val repository: CodeCategoryCrudRepository
) : CodeCategoryRepository {

    override fun save(codeCategory: CodeCategory): CodeCategory = repository.save(codeCategory)

    override fun getByCodeCategoryId(codeCategoryId: String): CodeCategory = findByCodeCategoryId(codeCategoryId) ?: throw NoSuchElementException("코드 카테고리가 존재하지 않습니다.")

    override fun findByCodeCategoryId(codeCategoryId: String, deleted: Boolean): CodeCategory? = repository.findByCodeCategoryId(codeCategoryId, deleted).orElse(null)

    override fun findAll(): List<CodeCategory> = repository.findAll().toList()

}
