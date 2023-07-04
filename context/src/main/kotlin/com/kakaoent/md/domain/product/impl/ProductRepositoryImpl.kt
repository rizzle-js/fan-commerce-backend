package com.kakaoent.md.domain.product.impl

import com.kakaoent.md.domain.product.Product
import com.kakaoent.md.domain.product.ProductRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

internal interface ProductCrudRepository : CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    fun findByProductId(productId: String): Optional<Product>

    @Query("""
        SELECT p
        FROM Product p
        JOIN MallProduct mp ON p.productId = mp.productId
        WHERE mp.mallId = :mallId AND p.deleted = false
    """)
    fun findByMallId(mallId: String, pageable: Pageable): Slice<Product>

    override fun findAll(spec: Specification<Product>): List<Product>
}

@Repository
internal class ProductRepositoryImpl(
    private val repository: ProductCrudRepository,
) : ProductRepository {
    override fun save(product: Product): Product = repository.save(product)

    override fun findByProductId(productId: String): Product? = repository.findByProductId(productId).orElse(null)

    override fun findAll(spec: Specification<Product>): List<Product> = repository.findAll(spec)

    override fun findByMallId(mallId: String, pageable: Pageable): Slice<Product> = repository.findByMallId(mallId, pageable)
}
