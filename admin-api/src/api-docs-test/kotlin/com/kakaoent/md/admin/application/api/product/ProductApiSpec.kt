package com.kakaoent.md.admin.application.api.product

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.DELETE_PRODUCT
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.GET_PRODUCT_DETAIL
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.REGISTER_PRODUCT
import com.kakaoent.md.admin.application.api.product.ProductController.Companion.UPDATE_PRODUCT
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.*
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

        test("상품 상세 정보를 조회하다") {
            mockMvc.perform(
                get(GET_PRODUCT_DETAIL, PRODUCT_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "ProductApiSpec 상품 상세 정보 조회",
                pathVariables {
                    "productId" means "상품 ID"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "quantity" type NUMBER means "재고"
                    "status" type STRING means "상품 상태"
                    "description" type STRING means "상품 설명"
                    "registeredAt" type NUMBER means "등록 날짜"
                }
            )
        }

        test("상품을 등록하다") {
            val request = RegisterProductRequest(
                name = "상품1",
                price = 1000,
                quantity = 100,
                status = ProductStatus.ON_SALE
            )
            mockMvc.perform(
                post(REGISTER_PRODUCT)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "ProductApiSpec 상품 등록",
                requestBody {
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "quantity" type NUMBER means "재고"
                    "status" type STRING means "상품 상태"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "quantity" type NUMBER means "재고"
                    "status" type STRING means "상품 상태"
                    "registeredAt" type NUMBER means "등록 날짜"
                }
            )
        }

        test("상품을 수정하다") {
            val productId = "some-product-id"
            val request = UpdateProductRequest(
                name = "상품1",
                price = 1000,
                quantity = 100,
                status = ProductStatus.ON_SALE
            )
            mockMvc.perform(
                put(UPDATE_PRODUCT, productId)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "ProductApiSpec 상품 수정",
                pathVariables {
                    "productId" means "상품 ID"
                },
                requestBody {
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "quantity" type NUMBER means "재고"
                    "status" type STRING means "상품 상태"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "quantity" type NUMBER means "재고"
                    "status" type STRING means "상품 상태"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }

        test("상품을 삭제하다") {
            mockMvc.perform(
                delete(DELETE_PRODUCT, PRODUCT_UUID)
                    .contentType(APPLICATION_JSON)
            ).andDocument(
                "ProductApiSpec 상품 삭제",
                pathVariables {
                    "productId" means "상품 ID"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "deletedAt" type NUMBER means "삭제 날짜"
                }
            )
        }
    }
}