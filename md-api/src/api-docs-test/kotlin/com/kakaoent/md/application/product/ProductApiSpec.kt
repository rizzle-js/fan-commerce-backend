package com.kakaoent.md.application.product

import com.kakaoent.md.application.*
import com.kakaoent.md.application.product.ProductController.Companion.CANCEL_PRODUCT
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PRODUCT_AVAILABILITY
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PRODUCT_CANCELLATION
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PURCHASE_PERMISSION
import com.kakaoent.md.application.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.application.product.ProductController.Companion.GET_PRODUCT_DETAIL
import com.kakaoent.md.application.product.ProductController.Companion.PURCHASE_PRODUCT
import com.kakaoent.md.application.product.ProductController.Companion.RATE_PRODUCT
import com.kakaoent.md.application.product.ProductController.Companion.RATE_PRODUCT_DETAIL
import com.kakaoent.md.config.objectMapper
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {

    init {

        test("상품 목록을 조회하다") {
            mockMvc.perform(
                get(
                    "${GET_PRODUCTS}?channelId={channelId}",
                    CHANNEL_UUID
                ).contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 목록 조회",
                queryParams {
                    "channelId" means "채널. 채널 ID와 컨텐츠 ID 중 하나는 필수"
                },
                responseBody {
                    "products[]" type ARRAY means "상품 목록"
                    "products[].productId" type STRING means "상품 ID"
                    "products[].name" type STRING means "상품명"
                    "products[].status" type STRING means "상품 상태"
                    "products[].quantity" type NUMBER means "상품 수량"
                    "products[].type" type STRING means "상품 타입"
                    "products[].price" type NUMBER means "가격"
                    "products[].tags" type ARRAY means "태그 목록"
                    "products[].productImageUrl" type STRING means "상품 이미지 URL"
                }
            )
        }

        test("상품 상세를 조회하다") {
            mockMvc.perform(
                get(GET_PRODUCT_DETAIL, PRODUCT_UUID)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 상세 조회",
                pathVariables {
                    "productId" means "상품 ID"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "name" type STRING means "상품명"
                    "status" type STRING means "상품 상태"
                    "type" type STRING means "상품 타입"
                    "relatedArtistId" type STRING means "연관 아티스트 ID"
                    "relatedArtistName" type STRING means "연관 아티스트명"
                    "relatedTicketId" type STRING means "연관 티켓 ID"
                    "relatedTicketName" type STRING means "연관 티켓명"
                    "price" type NUMBER means "가격"
                    "tags" type ARRAY means "태그 목록"
                    "description" type STRING means "상품 설명"
                    "productImageUrl" type STRING means "상품 이미지 URL"
                    "stock" type NUMBER means "상품 재고"
                    "shippingInfo" type STRING means "상품 배송 정보"
                }
            )
        }

        test("상품 구매 가능 여부를 확인하다") {
            mockMvc.perform(
                get(CHECK_PRODUCT_AVAILABILITY, PRODUCT_UUID)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 구매 가능 여부 확인",
                pathVariables {
                    "productId" means "상품 ID"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "status" type STRING means "상품 상태"
                }
            )
        }

        test("상품 구매 취소 가능 여부를 확인하다") {

            mockMvc.perform(
                get(CHECK_PRODUCT_CANCELLATION, PRODUCT_UUID)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 구매 취소 가능 여부 확인",
                pathVariables {
                    "productId" means "상품 ID"
                },
                responseBody {
                    "productId" type STRING means "상품 ID"
                    "cancellationPossible" type BOOLEAN means "취소 가능 여부"
                }
            )
        }

        test("상품 구매를 위한 권한을 확인하다") {

            mockMvc.perform(
                get(CHECK_PURCHASE_PERMISSION, PRODUCT_UUID, MEMBER_KEY)
                    .contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 구매를 위한 권한 확인",
                pathVariables {
                    "productId" means "상품 ID"
                    "memberKey" means "회원 키"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "purchasePossible" type BOOLEAN means "구매 가능 여부"
                }
            )
        }

        test("상품을 구매하다") {
            val request = PurchaseProductRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                quantity = 2,
                paymentMethod = PaymentMethod.CARD,
                deliveryAddress = "서울시 강남구"
            )

            mockMvc.perform(
                post(PURCHASE_PRODUCT)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "상품 구매",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "quantity" type NUMBER means "구매 수량"
                    "paymentMethod" type STRING means "결제 방식"
                    "deliveryAddress" type STRING means "배송 주소"
                },
                responseBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "quantity" type NUMBER means "구매 수량"
                    "purchaseAt" type NUMBER means "구매 날짜"
                    "paymentMethod" type STRING means "결제 방식"
                    "paymentAmount" type NUMBER means "결제 금액"
                    "deliveryAddress" type STRING means "배송 주소"
                }
            )
        }

        test("상품 구매를 취소하다") {
            val request = CancelProductRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                reason = "Reason1... 상품 취소해주세요. lol",
                refundMethod = RefundMethod.ORIGINAL_PAYMENT_METHOD
            )

            mockMvc.perform(
                post(CANCEL_PRODUCT)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "상품 구매 취소",
                requestBody {
                    "memberKey" type NUMBER means "회원 키"
                    "productId" type STRING means "상품 ID"
                    "reason" type STRING means "취소 사유"
                    "refundMethod" type STRING means "환불 수단"
                },
                responseBody {
                    "memberKey" type NUMBER means "사용자 ID"
                    "productId" type STRING means "상품 ID"
                    "reason" type STRING means "취소 사유"
                    "cancellationAt" type NUMBER means "구매 취소 날짜"
                    "refundMethod" type STRING means "환불 수단"
                    "refundAmount" type NUMBER means "환불 금액"
                    "refundStatus" type STRING means "환불 처리 상태"
                }
            )
        }

        test("상품을 평가하다") {
            val request = RateProductRequest(
                memberKey = MEMBER_KEY,
                productId = PRODUCT_UUID,
                rate = 10,
                comment = "상품평. 좋아요 :)"
            )

            mockMvc.perform(
                post(RATE_PRODUCT)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
            ).andDocument(
                "상품 평가",
                requestBody {
                    "memberKey" type NUMBER means "사용자 ID"
                    "productId" type STRING means "상품 ID"
                    "rate" type NUMBER means "평가 점수"
                    "comment" type STRING means "평가 내용"
                },
                responseBody {
                    "memberKey" type NUMBER means "사용자 ID"
                    "productId" type STRING means "상품 ID"
                    "rateId" type STRING means "평가 ID"
                    "ratedAt" type NUMBER means "평가 시간"
                }
            )
        }

        test("상품 평가를 조회하다") {
            mockMvc.perform(
                get(RATE_PRODUCT_DETAIL, RATE_UUID).contentType(APPLICATION_JSON_VALUE)
            ).andDocument(
                "상품 평가 조회",
                pathVariables {
                    "rateId" means "평가 ID"
                },
                responseBody {
                    "memberKey" type NUMBER means "사용자 ID"
                    "productId" type STRING means "상품 ID"
                    "rateId" type STRING means "평가 ID"
                    "comment" type STRING means "평가 내용"
                    "rate" type NUMBER means "평가 점수"
                    "ratedAt" type NUMBER means "평가 시간"
                    "reviewImages" type ARRAY means "리뷰 이미지"
                }
            )
        }
    }

}
