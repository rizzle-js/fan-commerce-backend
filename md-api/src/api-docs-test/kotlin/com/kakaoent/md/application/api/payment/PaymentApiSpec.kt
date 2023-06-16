package com.kakaoent.md.application.api.payment

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.order.ORDER_UUID
import com.kakaoent.md.application.api.payment.PaymentController.Companion.COMPLETE_PAYMENT
import com.kakaoent.md.application.api.payment.PaymentController.Companion.GET_PAYMENT_METHODS
import com.kakaoent.md.application.api.payment.PaymentController.Companion.REQUEST_PAYMENT
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [PaymentController::class])
class PaymentApiSpec : ApiSpec() {
    init {
        test("결제 수단 조회") {
            mockMvc.perform(
                get(GET_PAYMENT_METHODS)
                    .contentType(APPLICATION_JSON)
                    .param("memberKey", MEMBER_KEY.toString())
            ).andDocument(
                "PaymentApiSpec 결제 수단 조회",
                queryParams {
                    "memberKey" means "회원 키"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "paymentMethods[]" type ARRAY means "결제 수단 리스트"
                    "paymentMethods[].name" type STRING means "결제 수단명"
                    "paymentMethods[].details" type STRING means "결제 수단 상세"
                }
            )
        }

        test("상품 결제 요청") {
            val request = PaymentRequest(
                memberKey = MEMBER_KEY,
                orderId = ORDER_UUID,
                paymentMethodId = PAYMENT_METHOD_UUID,
                amount = 1000
            )

            mockMvc.perform(
                post(REQUEST_PAYMENT)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "PaymentApiSpec 상품 결제 요청",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "paymentMethodId" type STRING means "결제 수단 ID"
                    "amount" type NUMBER means "결제 금액"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "paymentId" type STRING means "결제 ID"
                    "paymentMethodId" type STRING means "결제 수단 ID"
                    "amount" type NUMBER means "결제 금액"
                    "requestedAt" type NUMBER means "결제 요청 날짜"
                }
            )
        }

        test("상품 결제 완료") {
            val request = CompletePaymentRequest(
                memberKey = 1,
                orderId = "order123",
                paymentMethodId = "method123",
                amount = 1000
            )

            mockMvc.perform(
                post(COMPLETE_PAYMENT, PAYMENT_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "PaymentApiSpec 상품 결제 완료",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "paymentMethodId" type STRING means "결제 수단 ID"
                    "amount" type NUMBER means "결제 금액"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "paymentMethodId" type STRING means "결제 수단 ID"
                    "amount" type NUMBER means "결제 금액"
                    "completedAt" type NUMBER means "결제 완료 날짜"
                }
            )
        }
    }
}
