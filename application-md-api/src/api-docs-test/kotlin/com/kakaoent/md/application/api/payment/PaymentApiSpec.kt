package com.kakaoent.md.application.api.payment

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.MEMBER_KEY
import com.kakaoent.md.application.api.order.ORDER_UUID
import com.kakaoent.md.application.api.payment.PaymentController.Companion.CANCEL_PAYMENT
import com.kakaoent.md.application.api.payment.PaymentController.Companion.GET_PAYMENT_METHODS
import com.kakaoent.md.application.api.payment.PaymentController.Companion.REQUEST_PAYMENT
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.*
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [PaymentController::class])
class PaymentApiSpec : ApiSpec() {
    @MockkBean
    lateinit var paymentController: PaymentController

    init {
        test("결제 수단 조회") {
            every { paymentController.getPaymentMethods(any()) } returns PaymentMethodResponse(
                MEMBER_KEY,
                listOf(
                    PaymentMethod(PaymentOption.DKPG)
                )
            )

            mockMvc.perform(
                get(GET_PAYMENT_METHODS)
                    .accept(APPLICATION_JSON)
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
                }
            )
        }

        xtest("상품 결제 요청") {
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


        xtest("상품 결제 취소 요청") {
            val request = CancelPaymentRequest(
                memberKey = 1,
                orderId = "order123"
            )

            mockMvc.perform(
                post(CANCEL_PAYMENT, PAYMENT_UUID)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "PaymentApiSpec 상품 결제 취소 요청",
                pathVariables {
                    "paymentId" means "결제 ID"
                },
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "paymentId" type STRING means "결제 ID"
                    "cancelledAt" type NUMBER means "취소 요청 날짜"
                }
            )
        }
    }
}
