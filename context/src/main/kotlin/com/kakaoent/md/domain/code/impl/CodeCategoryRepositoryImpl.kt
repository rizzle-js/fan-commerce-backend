package com.kakaoent.md.domain.code.impl

import com.kakaoent.md.domain.code.CodeCategory
import com.kakaoent.md.domain.code.CodeCategoryRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

internal interface CodeCategoryCrudRepository : CrudRepository<CodeCategory, Long> {

    @Query("SELECT c FROM CodeCategory c join fetch c._codes WHERE c.name = :name AND c.deleted = :deleted")
    fun findByName(name: String, deleted: Boolean): Optional<CodeCategory>

    @Query("SELECT DISTINCT c FROM CodeCategory c join fetch c._codes WHERE c.deleted = false")
    override fun findAll(): List<CodeCategory>
}

@Repository
internal class CodeCategoryRepositoryImpl(
    private val repository: CodeCategoryCrudRepository
) : CodeCategoryRepository {

    override fun save(codeCategory: CodeCategory): CodeCategory = repository.save(codeCategory)

    override fun delete(codeCategory: CodeCategory) {
        codeCategory.delete()
        repository.save(codeCategory)
    }

    override fun getByName(name: String): CodeCategory = findByName(name) ?: throw NoSuchElementException("코드 카테고리가 존재하지 않습니다.")

    override fun findByName(name: String): CodeCategory? = repository.findByName(name, false).orElse(null)

    override fun findAll(): List<CodeCategory> = repository.findAll()

}
