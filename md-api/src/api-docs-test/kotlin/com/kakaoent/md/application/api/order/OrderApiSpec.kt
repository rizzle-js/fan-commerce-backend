package com.kakaoent.md.application.api.order

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.andDocument
import com.kakaoent.md.application.api.order.OrderController.Companion.CHECKOUT
import com.kakaoent.md.application.api.requestBody
import com.kakaoent.md.application.api.responseBody
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.payload.JsonFieldType.*
import org.springframework.test.web.servlet.post

@WebMvcTest(controllers = [OrderController::class])
class OrderApiSpec : ApiSpec() {
    init {
        test("상품 체크아웃") {
            val checkoutRequest = CheckoutRequest(
                memberKey = 1L,
                orderItems = listOf(
                    OrderItem(
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

            mockMvc.post(CHECKOUT) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(checkoutRequest)
            }.andDocument(
                "상품 체크아웃",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderItems[]" type ARRAY means "주문 상품 목록"
                    "orderItems[].productId" type STRING means "상품 ID"
                    "orderItems[].productName" type STRING means "상품명"
                    "orderItems[].price" type NUMBER means "가격"
                    "orderItems[].quantity" type NUMBER means "수량"
                    "paymentMethod" type STRING means "결제 방식"
                    "deliveryInfo" type OBJECT means "배송지 정보"
                    "deliveryInfo.recipientName" type STRING means "수령인 이름"
                    "deliveryInfo.recipientPhone" type STRING means "수령인 전화번호"
                    "deliveryInfo.deliveryAddress" type STRING means "배송지 주소"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "orderItems[]" type ARRAY means "주문 상품 목록"
                    "orderItems[].productId" type STRING means "상품 ID"
                    "orderItems[].productName" type STRING means "상품명"
                    "orderItems[].price" type NUMBER means "가격"
                    "orderItems[].quantity" type NUMBER means "수량"
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
    }
}
