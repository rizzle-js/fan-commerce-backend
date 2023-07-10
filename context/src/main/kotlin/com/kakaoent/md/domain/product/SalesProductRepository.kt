package com.kakaoent.md.domain.product

import org.springframework.data.jpa.domain.Specification

interface SalesProductRepository {

    fun save(salesProduct: SalesProduct): SalesProduct

    fun findBySalesProductId(salesProductId: String): SalesProduct?

    fun findByProductId(productId: String): List<SalesProduct>

    fun findOne(spec: Specification<SalesProduct>): SalesProduct?

    fun findAll(spec: Specification<SalesProduct>): List<SalesProduct>

}
