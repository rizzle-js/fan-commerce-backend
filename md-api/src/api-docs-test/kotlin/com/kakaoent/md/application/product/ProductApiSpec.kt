package com.kakaoent.md.application.product

import com.kakaoent.md.application.ApiSpec
import com.kakaoent.md.application.andDocument
import com.kakaoent.md.application.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.application.queryParams
import com.kakaoent.md.application.responseBody
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType

@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {

    @Test
    fun `상품 목록을 조회하다`() {
        mockMvc.perform(
            get(GET_PRODUCTS).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "상품 목록 조회",
            queryParams {
                "channel" means "채널. 채널 ID와 컨텐츠 ID 중 하나는 필수"
            },
            responseBody {
                "products[]" type JsonFieldType.ARRAY means "상품 목록"
                "products[].productId" type JsonFieldType.NUMBER means "상품 ID"
                "products[].name" type JsonFieldType.STRING means "상품명"
                "products[].price" type JsonFieldType.NUMBER means "가격"
                "products[].tags" type JsonFieldType.ARRAY means "태그 목록"
                "products[].productImageUrl" type JsonFieldType.STRING means "상품 이미지 URL"
            }
        )
    }

}
