package com.kakaoent.md.application.api.product

import com.kakaoent.md.PagingParams
import com.kakaoent.md.domain.product.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun getProducts(
        mallId: String,
        tags: List<String>,
        sortOption: ProductSortOption,
        pagingParam: PagingParams,
    ): ProductsResponse {

        val pageable = with(pagingParam) { PageRequest.of(page, size, sortOption.sort) }

        val productsResults = productRepository.findByMallId(mallId, pageable)

        return ProductsResponse(
            products = productsResults.content.map { ProductView.from(it) },
            hasMore = productsResults.hasNext(),
        )
    }
}
