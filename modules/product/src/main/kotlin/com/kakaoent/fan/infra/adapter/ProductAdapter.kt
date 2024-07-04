package com.kakaoent.fan.infra.adapter

import com.kakaoent.fan.application.domain.Product
import com.kakaoent.fan.infra.adapter.persistence.ProductJpaRepository
import com.kakaoent.fan.port.outbound.ProductOut
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class ProductAdapter(
    private val productRepository: ProductJpaRepository
) : ProductOut {

    override fun get(id: Long): Product {
        return productRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Product not found")
    }

    override fun save(product: Product): Product {
        return productRepository.save(product)
    }
}
