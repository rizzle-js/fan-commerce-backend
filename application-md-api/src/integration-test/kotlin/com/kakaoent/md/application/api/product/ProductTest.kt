package com.kakaoent.md.application.api.product

import com.kakaoent.md.IntegrationTestSpec
import com.kakaoent.md.application.api.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.domain.mall.MallProduct
import com.kakaoent.md.domain.mall.MallProductRepository
import com.kakaoent.md.domain.product.ProductRepository
import com.kakaoent.md.fixture.product.mallProduct
import com.kakaoent.md.fixture.product.product
import com.kakaoent.md.responseBody
import io.kotest.matchers.collections.shouldBeSortedWith
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition
import java.time.Instant

class ProductTest(
    private val mockMvc: MockMvc,
    private val productRepository: ProductRepository,
    private val mallProductRepository: MallProductRepository,
    private val platformTransactionManager: PlatformTransactionManager
) : IntegrationTestSpec({

    fun transactional(block: () -> Unit) {
        val transaction = platformTransactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            block()
            platformTransactionManager.commit(transaction)
        } catch (e: Exception) {
            throw e
        }
    }

    feature("상품(Product)") {

        scenario("상품 목록을 조회하다") {

            val mallId = "mallId"

            val now = Instant.now()

            transactional {
                productRepository.save(product(productId = "1", displayedAt = now))
                productRepository.save(product(productId = "2", displayedAt = now.minusSeconds(100)))
                productRepository.save(product(productId = "3", displayedAt = now.minusSeconds(200)))
                productRepository.save(product(productId = "4", displayedAt = now.minusSeconds(300)))
                productRepository.save(product(productId = "5", displayedAt = now.minusSeconds(400)))

                mallProductRepository.save(mallProduct(mallId = mallId, productId = "1"))
                mallProductRepository.save(mallProduct(mallId = mallId, productId = "2"))
                mallProductRepository.save(mallProduct(mallId = mallId, productId = "3"))
            }

            val page = "0"
            val size = "2"
            val sort = "RECENT"

            mockMvc.get(GET_PRODUCTS) {
                param("mallId", mallId)
                param("sort", sort)
                param("page", page)
                param("size", size)
            }.andExpect {
                status { isOk() }
            }.responseBody<ProductsResponse>()
                .also {
                    it.products.size shouldBe size.toInt()
                    it.products shouldBeSortedWith compareByDescending { p ->  p.displayedAt }
                    it.hasMore shouldBe true
                }
        }
    }
})
