package com.kakaoent.md.application.api.product

import com.kakaoent.md.PagingParams
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping(GET_PRODUCTS)
    fun getProducts(
        @RequestParam("mallId", required = true) mallId: String,
        @RequestParam("tags", required = false) tags: List<String>?,
        @RequestParam("sort", required = false) sortOption: ProductSortOption?,
        pagingParam: PagingParams,
    ): ProductsResponse {
        return productService.getProducts(
            mallId = mallId,
            tags = tags ?: emptyList(),
            sortOption = sortOption ?: ProductSortOption.RECENT,
            pagingParam = pagingParam
        )
    }

    @GetMapping(GET_PRODUCT)
    fun getProduct(
        @PathVariable("productId") productId: String,
    ): ProductResponse {
        return productService.getProduct(productId)
    }

    companion object {
        const val GET_PRODUCTS = "/products"
        const val GET_PRODUCT = "/products/{productId}"
    }
}