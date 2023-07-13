package com.kakaoent.md.domain.product

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.domain.Specification

interface ProductRepository {

    fun save(product: Product): Product

    fun getByProductId(productId: String): Product = findByProductId(productId) ?: throw NoSuchElementException(productId)

    fun findByProductId(productId: String): Product?

    fun findOne(spec: Specification<Product>): Product?

    fun findAll(spec: Specification<Product>): List<Product>

    fun findByMallId(mallId: String, pageable: Pageable): Slice<Product>

}
