package com.kakaoent.md.application.api.cart

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.MEMBER_KEY
import com.kakaoent.md.application.api.cart.CartController.Companion.ADD_PRODUCT_TO_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.CLEAN_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.GET_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.REMOVE_PRODUCT_FROM_CART
import com.kakaoent.md.application.api.cart.CartController.Companion.UPDATE_PRODUCT_QUANTITY_IN_CART
import com.kakaoent.md.application.api.product.PRODUCT_UUID
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [CartController::class])
class CartApiSpec : ApiSpec() {

    init {
        test("장바구니를 조회하다") {
            mockMvc.perform(
                get(GET_CART, MEMBER_KEY).contentType(APPLICATION_JSON)
            ).andDocument(
                "CartApiSpec 장바구니 조회",
                pathVariables {
                    "memberKey" means "회원 키"
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
                productId = PRODUCT_UUID,
                quantity = 1
            )

            mockMvc.perform(
                post(ADD_PRODUCT_TO_CART, MEMBER_KEY).contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CartApiSpec 장바구니 담기",
                pathVariables {
                    "memberKey" means "회원 키"
                },
                requestBody {
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
                quantity = 2
            )

            mockMvc.perform(
                put(UPDATE_PRODUCT_QUANTITY_IN_CART, MEMBER_KEY, PRODUCT_UUID).contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "CartApiSpec 장바구니 상품 수량 수정",
                pathVariables {
                    "memberKey" means "회원 키"
                    "productId" means "상품 ID"
                },
                requestBody {
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
            mockMvc.perform(
                delete(REMOVE_PRODUCT_FROM_CART, MEMBER_KEY, PRODUCT_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "CartApiSpec 장바구니 상품 제거",
                pathVariables {
                    "memberKey" means "회원 키"
                    "productId" means "상품 ID"
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
            mockMvc.perform(
                delete(CLEAN_CART, MEMBER_KEY).contentType(APPLICATION_JSON)
            ).andDocument(
                "CartApiSpec 장바구니 비우기",
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
