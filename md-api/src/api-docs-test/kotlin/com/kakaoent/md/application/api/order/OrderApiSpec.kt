package com.kakaoent.md.application.api.order

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.order.OrderController.Companion.CANCEL_ORDER
import com.kakaoent.md.application.api.order.OrderController.Companion.GET_PURCHASE_HISTORY
import com.kakaoent.md.application.api.order.OrderController.Companion.PROCESS_CHECKOUT
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [OrderController::class])
class OrderApiSpec : ApiSpec() {
    init {
        test("상품 체크아웃") {
            val checkoutRequest = CheckoutRequest(
                memberKey = 1L,
                orderProducts = listOf(
                    OrderProduct(
                        productId = "product123",
                        productName = "상품1",
                        price = 1000,
                        quantity = 2
                    )
                ),
                paymentMethod = "credit_card",
                deliveryInfo = DeliveryInfo(
                    recipientName = "홍길동",
                    recipientPhone = "01012345678",
                    deliveryAddress = "서울시 강남구 어딘가"
                )
            )

            mockMvc.perform(
                post(PROCESS_CHECKOUT).contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(checkoutRequest))
            ).andDocument(
                "OrderApiSpec 상품 체크아웃",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderProducts[]" type ARRAY means "주문 상품 목록"
                    "orderProducts[].productId" type STRING means "상품 ID"
                    "orderProducts[].productName" type STRING means "상품명"
                    "orderProducts[].price" type NUMBER means "가격"
                    "orderProducts[].quantity" type NUMBER means "수량"
                    "paymentMethod" type STRING means "결제 방식"
                    "deliveryInfo" type OBJECT means "배송지 정보"
                    "deliveryInfo.recipientName" type STRING means "수령인 이름"
                    "deliveryInfo.recipientPhone" type STRING means "수령인 전화번호"
                    "deliveryInfo.deliveryAddress" type STRING means "배송지 주소"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "orderProducts[]" type ARRAY means "주문 상품 목록"
                    "orderProducts[].productId" type STRING means "상품 ID"
                    "orderProducts[].productName" type STRING means "상품명"
                    "orderProducts[].price" type NUMBER means "가격"
                    "orderProducts[].quantity" type NUMBER means "수량"
                    "paymentMethod" type STRING means "결제 방식"
                    "totalPayment" type NUMBER means "결제 총액"
                    "checkedOutAt" type NUMBER means "체크아웃 시각"
                    "deliveryInfo" type OBJECT means "배송지 정보"
                    "deliveryInfo.recipientName" type STRING means "수령인 이름"
                    "deliveryInfo.recipientPhone" type STRING means "수령인 전화번호"
                    "deliveryInfo.deliveryAddress" type STRING means "배송지 주소"
                }
            )
        }

        test("상품 구매내역 조회") {
            mockMvc.perform(
                get(GET_PURCHASE_HISTORY)
                    .contentType(APPLICATION_JSON)
                    .param("memberKey", MEMBER_KEY.toString())
            ).andDocument(
                "OrderApiSpec 상품 구매내역 조회",
                queryParams {
                    "memberKey" means "회원 키"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orders[]" type ARRAY means "주문 목록"
                    "orders[].orderId" type STRING means "주문 ID"
                    "orders[].purchasedProducts[]" type ARRAY means "주문 상품 목록"
                    "orders[].purchasedProducts[].productId" type STRING means "상품 ID"
                    "orders[].purchasedProducts[].productName" type STRING means "상품명"
                    "orders[].purchasedProducts[].price" type NUMBER means "가격"
                    "orders[].purchasedProducts[].quantity" type NUMBER means "상품 수량"
                    "orders[].paymentAmount" type NUMBER means "결제 금액"
                    "orders[].orderAt" type NUMBER means "주문 날짜"
                }
            )
        }

        test("주문 부분 취소 요청") {
            mockMvc.perform(
                post(CANCEL_ORDER, ORDER_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            CancelOrderRequest(
                                memberKey = 1L,
                                cancelProducts = listOf(
                                    CancelProduct(
                                        productId = "product123",
                                        productName = "상품1",
                                        cancelPrice = 10000,
                                        cancelQuantity = 2
                                    )
                                ),
                            )
                        )
                    )
            ).andDocument(
                "OrderApiSpec 주문 부분 취소 요청",
                pathVariables {
                    "orderId" means "주문 ID"
                },
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "cancelProducts[]" type ARRAY means "취소 상품 목록"
                    "cancelProducts[].productId" type STRING means "상품 ID"
                    "cancelProducts[].productName" type STRING means "상품명"
                    "cancelProducts[].cancelPrice" type NUMBER means "취소할 가격"
                    "cancelProducts[].cancelQuantity" type NUMBER means "취소할 상품 수량"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "cancelProducts[]" type ARRAY means "취소 된 상품 목록"
                    "cancelProducts[].productId" type STRING means "상품 ID"
                    "cancelProducts[].productName" type STRING means "상품명"
                    "cancelProducts[].cancelPrice" type NUMBER means "취소된 가격"
                    "cancelProducts[].cancelQuantity" type NUMBER means "취소된 상품 수량"
                    "cancelRequestedAt" type NUMBER means "취소 요청 날짜"
                }
            )
        }
    }
}
