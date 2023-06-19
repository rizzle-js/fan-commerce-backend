package com.kakaoent.md.application.api.delivery

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.application.api.MEMBER_KEY
import com.kakaoent.md.application.api.delivery.DeliveryController.Companion.PICKUP
import com.kakaoent.md.application.api.order.ORDER_UUID
import com.kakaoent.md.application.api.product.PRODUCT_UUID
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import com.kakaoent.md.config.serde.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType.NUMBER
import org.springframework.restdocs.payload.JsonFieldType.STRING

@WebMvcTest(controllers = [DeliveryController::class])
class DeliveryApiSpec : ApiSpec() {
    init {
        test("상품 현장 수령") {
            val request = PickupRequest(
                memberKey = MEMBER_KEY,
                orderId = ORDER_UUID,
                productId = PRODUCT_UUID,
                quantity = 1
            )

            mockMvc.perform(
                post(PICKUP)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "DeliveryApiSpec 상품 현장 수령",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "productId" type STRING means "상품 ID"
                    "quantity" type NUMBER means "상품 수량"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "orderId" type STRING means "주문 ID"
                    "productId" type STRING means "상품 ID"
                    "productName" type STRING means "상품명"
                    "quantity" type NUMBER means "상품 수량"
                    "pickedUpAt" type NUMBER means "수령 날짜"
                }
            )
        }
    }
}
