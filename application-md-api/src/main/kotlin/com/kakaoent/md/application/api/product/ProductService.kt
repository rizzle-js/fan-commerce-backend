package com.kakaoent.md.application.api.product

import com.kakaoent.md.PagingParams
import com.kakaoent.md.domain.product.ProductRepository
import com.kakaoent.md.support.Language
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
            products = productsResults.content.map { ProductView.of(it) },
            hasMore = productsResults.hasNext(),
        )
    }

    fun getProduct(productId: String, language: Language = Language.KOREAN): ProductResponse {

        val product = productRepository.getByProductId(productId)

        return ProductResponse(
            productId = product.productId,
            name = product.getName(language),
            price = product.price,
            optionGroups = product.productOptionGroups.map { optionGroup ->
                ProductOptionGroupView(
                    productOptionGroupId = optionGroup.id,
                    name = optionGroup.name,
                    options = optionGroup.productOptions.map { option ->
                        ProductOptionView(
                            productOptionId = option.id,
                            value = option.value,
                        )
                    }
                )
            },
            receiving = ReceivingView(
                type = product.receiving.type,
                receivingDates = product.receiving.receivingDates.map { receivingDate ->
                    ReceivingDateView(
                        date = receivingDate.date,
                    )
                }
            ),
            badges = ProductBadges(
                isNew = false, // todo
            )
        )
    }
}
