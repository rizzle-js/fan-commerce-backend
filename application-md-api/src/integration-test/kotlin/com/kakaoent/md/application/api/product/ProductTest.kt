package com.kakaoent.md.application.api.product

import com.kakaoent.md.IntegrationTestSpec
import com.kakaoent.md.application.api.product.ProductController.Companion.GET_PRODUCT_DETAIL
import com.kakaoent.md.responseBody
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class ProductTest(mockMvc: MockMvc) : IntegrationTestSpec({

    feature("상품(Product)") {

        scenario("상품을 조회하다") {

            val productId = "1"

            mockMvc.get(GET_PRODUCT_DETAIL, productId) {
                contentType = APPLICATION_JSON
            }
                .andExpect {
                    status { isOk() }
                }
                .responseBody<ProductDetailResponse>()
                .also {
                    it.productId shouldBe productId
                }
        }
    }
})
