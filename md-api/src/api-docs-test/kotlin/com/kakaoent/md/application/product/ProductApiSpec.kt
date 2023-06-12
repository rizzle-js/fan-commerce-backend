package com.kakaoent.md.application.product

import com.kakaoent.md.UuidGenerator
import com.kakaoent.md.application.*
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PRODUCT_AVAILABILITY
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PRODUCT_CANCELLATION
import com.kakaoent.md.application.product.ProductController.Companion.CHECK_PURCHASE_PERMISSION
import com.kakaoent.md.application.product.ProductController.Companion.GET_PRODUCTS
import com.kakaoent.md.application.product.ProductController.Companion.GET_PRODUCT_DETAIL
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType

@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {

    @Test
    fun `상품 목록을 조회하다`() {
        mockMvc.perform(
            get(
                "${GET_PRODUCTS}?channelId={channelId}",
                UuidGenerator.generate()
            ).contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "상품 목록 조회",
            queryParams {
                "channelId" means "채널. 채널 ID와 컨텐츠 ID 중 하나는 필수"
            },
            responseBody {
                "products[]" type JsonFieldType.ARRAY means "상품 목록"
                "products[].productId" type JsonFieldType.STRING means "상품 ID"
                "products[].name" type JsonFieldType.STRING means "상품명"
                "products[].status" type JsonFieldType.STRING means "상품 상태"
                "products[].quantity" type JsonFieldType.NUMBER means "상품 수량"
                "products[].type" type JsonFieldType.STRING means "상품 타입"
                "products[].price" type JsonFieldType.NUMBER means "가격"
                "products[].tags" type JsonFieldType.ARRAY means "태그 목록"
                "products[].productImageUrl" type JsonFieldType.STRING means "상품 이미지 URL"
            }
        )
    }

    @Test
    fun `상품 상세를 조회하다`() {
        mockMvc.perform(
            get(GET_PRODUCT_DETAIL, UuidGenerator.generate())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "상품 상세 조회",
            pathVariables {
                "productId" means "상품 ID"
            },
            responseBody {
                "productId" type JsonFieldType.STRING means "상품 ID"
                "name" type JsonFieldType.STRING means "상품명"
                "status" type JsonFieldType.STRING means "상품 상태"
                "type" type JsonFieldType.STRING means "상품 타입"
                "relatedArtistId" type JsonFieldType.STRING means "연관 아티스트 ID"
                "relatedArtistName" type JsonFieldType.STRING means "연관 아티스트명"
                "relatedTicketId" type JsonFieldType.STRING means "연관 티켓 ID"
                "relatedTicketName" type JsonFieldType.STRING means "연관 티켓명"
                "price" type JsonFieldType.NUMBER means "가격"
                "tags" type JsonFieldType.ARRAY means "태그 목록"
                "description" type JsonFieldType.STRING means "상품 설명"
                "productImageUrl" type JsonFieldType.STRING means "상품 이미지 URL"
                "stock" type JsonFieldType.NUMBER means "상품 재고"
                "shippingInfo" type JsonFieldType.STRING means "상품 배송 정보"
            }
        )
    }

    @Test
    fun `상품 구매 가능 여부를 확인하다`() {
        mockMvc.perform(
            get(CHECK_PRODUCT_AVAILABILITY, UuidGenerator.generate())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "상품 구매 가능 여부 확인",
            pathVariables {
                "productId" means "상품 ID"
            },
            responseBody {
                "productId" type JsonFieldType.STRING means "상품 ID"
                "status" type JsonFieldType.STRING means "상품 상태"
            }
        )
    }

    @Test
    fun `구매 상품 취소 가능 여부를 확인하다`() {

        mockMvc.perform(
            get(CHECK_PRODUCT_CANCELLATION, UuidGenerator.generate())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "구매 상품 취소 가능 여부 확인",
            pathVariables {
                "productId" means "상품 ID"
            },
            responseBody {
                "productId" type JsonFieldType.STRING means "상품 ID"
                "cancellationPossible" type JsonFieldType.BOOLEAN means "취소 가능 여부"
            }
        )
    }

    @Test
    fun `상품 구매를 위한 권한을 확인하다`() {

        mockMvc.perform(
            get(CHECK_PURCHASE_PERMISSION, UuidGenerator.generate(), 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDocument(
            "상품 구매를 위한 권한 확인",
            pathVariables {
                "productId" means "상품 ID"
                "memberKey" means "회원 키"
            },
            responseBody {
                "memberKey" type JsonFieldType.NUMBER means "회원 키"
                "productId" type JsonFieldType.STRING means "상품 ID"
                "purchasePossible" type JsonFieldType.BOOLEAN means "구매 가능 여부"
            }
        )
    }
}
