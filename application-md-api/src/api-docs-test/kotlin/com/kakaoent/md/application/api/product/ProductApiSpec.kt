package com.kakaoent.md.application.api.product

import com.kakaoent.md.application.api.ApiSpec
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.queryParams
import com.kakaoent.md.docs.responseBody
import com.kakaoent.md.domain.product.Product
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.JsonFieldType.*
import java.time.Instant

@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {

    @MockkBean
    private lateinit var productController: ProductController

    init {

        test("상품 목록을 조회하다") {
            every { productController.getProducts(any(), any(), any(), any()) } returns ProductsResponse(
                products = listOf(
                    ProductView(
                        productId = "4012345678",
                        name = "[상품] 정말 비싼 상품",
                        status = Product.Status.ON_SALE,
                        price = 5000,
                        displayedAt = INSTANT,
                        thumbnailImageUrl = "https://sandbox-fan-static.melon.com/md/2023/7/3/image1.png",
                        badges = ProductBadges(
                            isNew = true,
                        )
                    ),
                    ProductView(
                        productId = "4012345678",
                        name = "[상품] 정말 싼 상품",
                        status = Product.Status.SOLD_OUT,
                        price = 5000,
                        displayedAt = INSTANT,
                        thumbnailImageUrl = "https://sandbox-fan-static.melon.com/md/2023/7/3/image2.png",
                        badges = ProductBadges(
                            isNew = true,
                        )
                    )
                ),
                hasMore = false
            )

            mockMvc.perform(
                get(ProductController.GET_PRODUCTS)
                    .param("mallId", MALL_ID)
                    .param("tags", "tag1", "tag2")
                    .param("sort", "RECENT")
                    .param("page", "1")
                    .param("size", "10")
            ).andDocument(
                "ProductApiSpec 상품 목록 조회",
                queryParams {
                    "mallId" means "몰 ID"
                    "tags" means "상품 태그 목록"
                    "sort" means "정렬 기준"
                    "page" means "페이지"
                    "size" means "페이지 크기"
                },
                responseBody {
                    "products[]" type ARRAY means "상품 목록"
                    "products[].productId" type STRING means "상품 ID"
                    "products[].name" type STRING means "상품 이름"
                    "products[].status" type STRING means "상품 상태"
                    "products[].price" type NUMBER means "상품 가격"
                    "products[].displayedAt" type NUMBER means "상품 등록일"
                    "products[].thumbnailImageUrl" type STRING means "상품 썸네일 이미지 URL"
                    "products[].badges" type OBJECT means "상품 뱃지"
                    "products[].badges.isNew" type BOOLEAN means "상품 뱃지 - 신상품"
                    "hasMore" type BOOLEAN means "불러올 수 있는 상품 유무"
                }
            )
        }
    }

    companion object {
        private const val MALL_ID = "mall-id"
        private val INSTANT = Instant.ofEpochSecond(1625308800)
    }
}

const val PRODUCT_UUID = "product-uuid"
