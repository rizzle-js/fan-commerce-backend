# Application API Module
> Application API를 정의하는 모듈



## IntegrationTest
> 통합 테스트는 @SpringBootTest를 사용하여 테스트를 진행한다. 테스트 라이브러리는 Kotest를 사용하며 Feature Spec를 확장한 IntegrationTestSpec로 시나리오 별로 테스트를 작성한다. 추후 테스트 성능이 저하되는 경우 @ContextConfigure를 사용해 테스트에 필요한 Bean만 명시적으로 등록한다.

### 통합 테스트 작성 규칙
- 테스트는 시나리오 별로 작성한다.
- 시나리오 성공/실패 Case 위주로 작성한다.
- 시나리오 실패 Case에서 단순 예외를 던지는 시나리오는 테스트를 작성하지 않는다.
  - 이 경우 통합 테스트가 아닌 단위 테스트에서 작성한다.
- 외부 모듈에 대한 의존성은 다음과 같이 설정한다.
  - Context: 실제 DB와 연동한다.
  - External-Service: 모듈에서 제공하는 Interface를 통해 Stub 객체를 구현한다. 할 수 있는 한 Mocking 하지 않는다.
- 테스트의 Given 데이터는 Fixture를 활용하여 생성한다.
- @Transactional를 사용하지 않는다.
  - 2종 오류가 발생할 수 있다.

### 테스트 작성 예시

```kotlin
@SpringBootTest
@AutoConfigureMockMvc
abstract class IntegrationTestSpec(body: FeatureSpec.() -> Unit) : FeatureSpec(body)

// 테스트 작성 ProductIntegrationTest.kt
class ProductIntegrationTest(
    mockMvc: MockMvc,
    productRepository: ProductRepository
) : IntegrationTestSpec({

    feature("상품(Product)") {
        scenario("상품 상세를 조회하다") {
            // 필요한 Given 데이터는 할 수 있는 한 Fixture를 활용해서 생성한다. 
            productRepository.save(product("productId"))

            mockMvc.get("/products/{productId}", "productId")
                .andExpect {
                    status { isOk() }
                }
                .andReturn()
                .responseBody<ProductDetailResponse>()
                .also {
                    // 검증
                    it.id shouldBe "productId"
                }
        }
    }
})
```



## Api Documentation Test
> Api Documentation Test는 @WebMvcTest와 @AutoConfigureRestDocs를 사용하여 테스트를 진행한다. 테스트 라이브러리는 Kotest를 사용하며 FunSpec를 확장한 ApiSpec로 시나리오 별로 테스트를 작성한다.

### Api Documentation Test 작성 규칙
- 모든 API에 대한 테스트를 작성해야 한다.
- 공통으로 사용할 상수는 CommonFixtures 파일에 작성한다.

### 테스트 작성 예시

```kotlin
@WebMvcTest(controllers = [ProductController::class])
class ProductApiSpec : ApiSpec() {
    init {
        test("상품 목록을 조회하다") {
            mockMvc.perform(
              get(GET_PRODUCTS)
                .param("channelId", CHANNEL_UUID)
                .contentType(APPLICATION_JSON_VALUE)
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
    }
}
```
