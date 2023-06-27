package com.kakaoent.md.domain.code.impl

import com.kakaoent.md.domain.code.Code
import com.kakaoent.md.domain.code.CodeCategory
import com.kakaoent.md.domain.code.CodeCategoryDomainService
import com.kakaoent.md.domain.code.CodeCategoryRepository
import com.kakaoent.md.fixture.code.codeCategory
import io.kotest.assertions.throwables.shouldThrowExactlyUnit
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Root
import org.hibernate.LazyInitializationException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import java.util.function.Predicate
import kotlin.reflect.KProperty1

@SpringBootTest
@ActiveProfiles("test")
class CodeCategoryTest(
    private val codeCategoryRepository: CodeCategoryRepository,
    private val codeCategoryDomainService: CodeCategoryDomainService,
    private val transactionManager: PlatformTransactionManager,
) : FunSpec({
    extension(SpringExtension)

    fun transactional(name: String, process: () -> Unit) {

        val tx = transactionManager.getTransaction(TransactionDefinition.withDefaults())

        println("########## $name start transactional: ${tx.isNewTransaction}")

        try {
            process()
            transactionManager.commit(tx)
        } catch (e: RuntimeException) {
            transactionManager.rollback(tx)
            println("########## $name end transactional")
            throw e
        }

        println("########## $name end transactional")
        println()
    }

    test("test") {
        transactional("준비") {
            val codeCategory = codeCategory("cc1", "code category 1")
            codeCategoryRepository.save(codeCategory)

            val code = Code(codeCategory, "code1", "code 1")
            codeCategory.addCode(code)
            codeCategoryRepository.save(codeCategory)

            println("준비 끝")
        }

        transactional("캐시 안탐") {
            val codeCategory = codeCategoryDomainService.getByName("cc1")
            println(codeCategory)
        }

        transactional("캐시 탐") {
            val codeCategory = codeCategoryDomainService.getByName("cc1")
            println(codeCategory)
        }

        transactional("변경 시 캐시 무효화됨") {
            val codeCategory = codeCategoryRepository.getByName("cc1")
            codeCategory.update(name = "cc2", description = "code category 2")
            codeCategoryRepository.save(codeCategory)
        }

        transactional("무효화 확인") {
            val codeCategory = codeCategoryDomainService.getByName("cc2")
            println(codeCategory)
        }
    }
})
