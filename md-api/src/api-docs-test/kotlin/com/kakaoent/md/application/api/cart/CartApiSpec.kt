package com.kakaoent.md.application.api.cart

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.cart.CartController.Companion.ADD_PRODUCT_TO_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.EMPTY_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.GET_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.REMOVE_PRODUCT_FROM_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.UPDATE_PRODUCT_QUANTITY_IN_CART
import com.kakaoent.md.application.api.product.PRODUCT_UUID
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@WebMvcTest(controllers = [CartController::class])
class CartApiSpec : ApiSpec() {

    init {
        test("장바구니를 조회하다") {
            mockMvc.get(GET_CART) {
                param("memberKey", MEMBER_KEY.toString())
                contentType = APPLICATION_JSON
            }.andDocument(
                "장바구니 조회",
                queryParams {
                    "memberKey" means "사용자 ID"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "cartId" type STRING means "장바구니 ID"
                    "products[]" type ARRAY means "장바구니 상품 목록"
                    "products[].productId" type STRING means "상품 ID"
                    "products[].name" type STRING means "상품명"
                    "products[].price" type NUMBER means "가격"
                    "products[].quantity" type NUMBER means "수량"
                    "products[].productImageUrl" type STRING means "상품 이미지 URL"
                }
            )
        }

        test("상품을 장바구니에 담다") {
            val request = AddProductToCartRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                quantity = 1
            )

            mockMvc.post(ADD_PRODUCT_TO_CART) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(request)
            }.andDocument(
                "장바구니 담기",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "quantity" type NUMBER means "수량"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "price" type NUMBER means "가격"
                    "addedAt" type NUMBER means "담은 날짜"
                    "cartId" type STRING means "장바구니 ID"
                    "quantity" type NUMBER means "수량"
                }
            )
        }

        test("장바구니의 상품 수량을 수정하다") {
            val request = UpdateProductQuantityInCartRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                quantity = 2
            )

            mockMvc.put(UPDATE_PRODUCT_QUANTITY_IN_CART) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(request)
            }.andDocument(
                "장바구니 상품 수량 수정",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "quantity" type NUMBER means "수량"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "updatedQuantity" type NUMBER means "변경된 수량"
                    "updatedAt" type NUMBER means "수정한 날짜"
                    "cartId" type STRING means "장바구니 ID"
                }
            )
        }

        test("장바구니에서 상품을 제거하다") {
            val request = RemoveProductFromCartRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID
            )

            mockMvc.delete(REMOVE_PRODUCT_FROM_CART) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(request)
            }.andDocument(
                "장바구니 상품 제거",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "removedAt" type NUMBER means "제거한 날짜"
                    "cartId" type STRING means "장바구니 ID"
                }
            )
        }

        test("장바구니를 비우다") {
            mockMvc.delete(EMPTY_CART, MEMBER_KEY) {
                contentType = APPLICATION_JSON
            }.andDocument(
                "장바구니 비우기",
                pathVariables {
                    "memberKey" means "사용자 ID"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "emptiedAt" type NUMBER means "비운 날짜"
                    "cartId" type STRING means "장바구니 ID"
                }
            )
        }
    }
}

