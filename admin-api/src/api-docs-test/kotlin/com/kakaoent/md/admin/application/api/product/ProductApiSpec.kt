package com.kakaoent.md.admin.application.api.product

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.queryParams
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {
    init {
        test("상품 목록을 조회하다") {
            mockMvc.perform(
                get(GET_PRODUCTS).contentType(APPLICATION_JSON)
                    .param("page", "0")
                    .param("size", "10")
            ).andDocument(
                "ProductApiSpec 상품 목록 조회",
                queryParams {
                    "page" means "페이지 번호"
                    "size" means "페이지 크기"
                },
                responseBody {
                    "products[]" type ARRAY means "상품 목록"
                    "products[].productId" type STRING means "상품 ID"
                    "products[].name" type STRING means "상품명"
                    "products[].price" type NUMBER means "가격"
                    "products[].quantity" type NUMBER means "재고"
                    "products[].status" type STRING means "상품 상태"
                    "products[].registeredAt" type NUMBER means "등록 날짜"
                }
            )
        }
    }
}