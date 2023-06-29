package com.kakaoent.md.application.api.payment

import com.kakaoent.md.IntegrationTestSpec
import com.kakaoent.md.application.api.payment.PaymentController.Companion.GET_PAYMENT_METHODS
import com.kakaoent.md.responseBody
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class PaymentTests(mockMvc: MockMvc) : IntegrationTestSpec({

    fun getPaymentMethods() {

    }

    feature("결제 수단을 조회하다") {

        scenario("멜론 사용자는 상품을 결제하기 위해 결제 수단을 조회할 수 있다") {
            io.kotest.data.forAll(
                // examples:
                table(
                    headers("memberKey"),
                    row(1L)
                )
            ) { memberKey ->
                // Given: 사용자 <memberKey> 가 있다
                // When: 결제 수단을 조회하면
                mockMvc.get(GET_PAYMENT_METHODS, memberKey) {
                    param("memberKey", memberKey.toString())
                    accept = APPLICATION_JSON
                }
                    // Then: 결제 수단을 확인할 수 있다
                    .andDo { print() }
                    .andExpect { status { isOk() } }
                    .responseBody<PaymentMethodResponse>()
                    .also {
                        it.memberKey shouldBe memberKey
                        it.paymentMethods.size shouldBe 1
                        it.paymentMethods[0].name shouldBe PaymentOption.DKPG
                    }
            }


        }
    }
})
