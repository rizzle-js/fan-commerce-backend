package com.kakaoent.md.application.api.payment

import com.kakaoent.md.application.api.*
import com.kakaoent.md.application.api.payment.PaymentController.Companion.GET_PAYMENT_METHODS
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
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
    }
}