package com.kakaoent.md.domain.product.impl

import com.kakaoent.md.domain.product.SalesProduct
import com.kakaoent.md.domain.product.SalesProductRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

internal interface SalesProductCrudRepository : CrudRepository<SalesProduct, Long>, JpaSpecificationExecutor<SalesProduct> {

    fun findBySalesProductId(salesProductId: String): Optional<SalesProduct>

    @Query(
        """
            select distinct sp
            from SalesProduct sp
            join fetch sp._salesProductOptions
            where sp.productId = :productId
        """
    )
    fun findByProductId(productId: String): List<SalesProduct>
}



@Repository
internal class SalesProductRepositoryImpl(
    private val repository: SalesProductCrudRepository
): SalesProductRepository {

    override fun save(salesProduct: SalesProduct): SalesProduct = repository.save(salesProduct)

    override fun findBySalesProductId(salesProductId: String): SalesProduct? = repository.findBySalesProductId(salesProductId).orElse(null)

    override fun findByProductId(productId: String): List<SalesProduct> = repository.findByProductId(productId)

    override fun findOne(spec: Specification<SalesProduct>): SalesProduct? = repository.findOne(spec).orElse(null)

    override fun findAll(spec: Specification<SalesProduct>): List<SalesProduct> = repository.findAll(spec)
}
