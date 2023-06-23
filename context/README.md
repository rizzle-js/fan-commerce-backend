# Context Module

> 도메인 모델 및 데이터 영속화 모듈

## 엔티티 구분
- 도메인 엔티티는 PK와 별개로 식별자를 가진다(도메인 식별자).
- 값 객체는 @Embeddable를 사용하되 OneToMany 관계일 때만 엔티티로 표현한다.
- 엔티티는 모두 BaseEntity를 상속받는다.
- 변경 이력을 관리해야 하는 엔티티는 AuditingEntity를 상속받는다.

## 엔티티 설계 가이드

1. 도메인 ID는 다음과 같이 설정한다
```kotlin
@Column(
  name = "product_id", 
  updatable = false, 
  unique = true, 
  nullable = false,
  length = DOMAIN_ID_LENGTH  
)
val productId: String
```

2. 가변 컬렉션은 Backing Property를 사용한다.
```kotlin
protected val _reviews: MutableList<Review> = mutableListOf()
val reviews: List<Review> get() = _reviews.toList()
```

3. 수정되지 않는 값은 val로 설정하고 생성자에서 초기화한다. 그리고 반드시 `updatable = false`로 설정한다.

(변경 감지로 생성한 update 쿼리에 포함되지 않는다. 따라서 prepared statement를 사용할 때 성능이 향상될 수 있다).
```kotlin

class Product(
  ...
  @Column(name = "price", nullable = false, updatable = false)
  val price: Int,
  ...
)
```
4. `@Transient` 대신 `프로퍼티 getter`를 사용하자. 메서드로 컴파일되기 때문에 영속화 대상이 아니면서도 프로퍼티로 사용할 수 있다.
```kotlin
  val totalPrice: Int get() = price * quantity
```
5. Column에 반드시 name을 작성하자.


6. enum 사용 시 `@Enumerated(EnumType.STRING)`을 사용하자.


7. `Embeddable`을 애용하자.

## JPA 연관 관계 설정 TIP
> 무조건 따를 필요는 없다. 필요에 따라 유연하게 변경하자.
- 연관 관계 맵핑(객체 참조)은 할 수 있는 한 사용하지 않는다.
  - 엔티티 관리가 복잡해지고, 테스트가 어려워진다.
- 같은 Context에 속하거나 CUD 트랜잭션으로 묶이는 엔티티들은 연관 관계를 설정한다.
  - 도메인의 생명 주기가 같은 엔티티들은 연관 관계를 설정한다.
- 다른 Context에 있는 엔티티는 해당 Context의 Aggregate Root ID로 참조한다.

### 연관 관계 설정 기준
`상품`과 `리뷰`는 1:N 관계이다. 하지만 상품과 리뷰는 각각 다른 생명 주기를 가진다. 따라서 이 경우 리뷰와 상품의 연관 관계를 설정하는 대신 리뷰에서 상품의 ID를 참조한다.

`구매 내역`과 `구매 상품`은 1:N 관계이다. 하지만 구매 상품은 구매 내역이 생성될 때 함께 생성된다. 즉, 구매 내역과 구매 상품은 같은 생명 주기를 가진다(트랜잭션으로 묶을 수 있다). 따라서 이 경우 구매 내역과 구매 상품의 연관 관계를 설정한다.

### One To One
`@OneToOne`은 가능하다면 `@Embedded`로 대체한다.

부모 엔티티에서 OneToOne 관계를 설정한다.

### One To Many
`@OneToMany`를 사용한다면 아래와 같은 문제가 발생할 수 있다.
- 1 + N 문제가 발생하기 쉽다.
- 하나의 `@OneToMany` 관계에만 fetch join을 적용할 수 있다.
- 페이징 적용 시 fetch join을 할 수 없다.
- Many에 해당하는 엔티티를 필터링하기 힘들다.
  - JPQL은 fetch join 대상에 Alias를 줄 수 없다(하이버네이트는 가능하지만 위험).

OneToMany를 적용하기 전 꼭 필요한지 한 번 더 생각하자.

만약 Many 쪽 엔티티의 수가 많다면 Context 분리를 고려하자.

또한 `@ManyToOne` 양방향 맵핑은 아래 이유에 해당된다면 적용한다.
  - Many에 해당하는 엔티티 대상 CUD 명령이 자주 수행된다.
  - Many에 해당하는 엔티티의 수가 많다.
  - 페이징이 필요하다.
    - 이 경우도 가능하다면 DTO Projection으로 해결하자.

## Soft Deletion
- 데이터를 실제로 삭제하는 대신 데이터에 삭제 여부만 표시한다.
- 삭제 여부를 표시하는 필드는 `deleted`로 한다.
- 특별한 이유가 없다면 엔티티는 Soft Delete로 관리한다.
- 특별한 이유(e.g, 삭제된 데이터가 너무 많은 경우)가 아니라면 어플리케이션에서 삭제된 데이터를 필터링한다.
- 하이버네이트의 `@SQLDelete`를 사용하지 않고 명시적으로 엔티티의 delete() 함수를 사용한다.
- 하이버네이트의 `@Where`은 클래스 레벨이 아닌 메서드 레벨에 적용한다.
  - 클래스 레벨에 설정하면 삭제 처리된 엔티티를 절대 조회하기 힘들다.
  - 삭제된 연관 엔티티를 조회할 필요가 없는 메서드에만 명시적으로 `@Where`를 적용한다.

### One To One 연관 관계

#### 1. 모두 Soft Delete인 경우
두 엔티티가 모두 Soft Delete인 경우 부모에서 자식를 명시적으로 삭제한다.
```kotlin
@Entity
@Table(name = "parent")
class Parent(
    child: Child
) : AuditingEntity() {

    @OneToOne(fetch = LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE], orphanRemoval = true)
    var child: Child = child
        protected set

    override fun delete() {
        super.delete()
        child.delete()
    }
}
```

#### 2.부모는 Soft Delete, 자식은 Hard Delete인 경우
부모 엔티티가 Soft Delete, 자식 엔티티는 Hard Delete를 사용하는 경우 부모만 삭제한다.
자식 엔티티는 쿼리를 통해 명시적으로 삭제한다.
(이런 경우가 있을까?)
```kotlin
@Entity
@Table(name = "parent")
class Parent(
    child: Child
) : AuditingEntity() {

    @OneToOne(fetch = LAZY, cascade = [PERSIST, MERGE])
    var child: Child = child
        protected set
  
  //부모만 delete
}
```
#### 3. 둘 다 Hard Delete인 경우
CascadeType.ALL + orphanRemoval = true를 적용한다.
```kotlin
@Entity
@Table(name = "parent")
class Parent(
    child: Child
) : AuditingEntity() {

    @OneToOne(fetch = LAZY, cascade = [ALL], orphanRemoval = true)
    var child: Child = child
        protected set
}
```

### One To Many 연관 관계

#### 1. 모두 Soft Delete인 경우
두 엔티티가 모두 Soft Delete인 경우 부모에서 자식을 명시적으로 삭제한다.

만약 삭제된 자식 엔티티를 조회할 필요가 없다면 `@Where`를 적용한다.
```kotlin
@Entity
@Table(name = "parent")
class Parent: AuditingEntity() {
    
    @OneToMany(mappedBy = "parent", cascade = [PERSIST, MERGE])
    @Where(clause = EXCLUDE_DELETION)
    protected val _children: MutableList<Child> = mutableListOf()
    val children: List<Child> get() = _children.toList()
    
    fun removeChild(child: Child) {
        _children.remove(child)
        child.delete()
    }
    
    override fun delete() {
        super.delete()
        children.onEach(Child::delete)
    }
}
```

#### 2. 부모는 Soft Delete, 자식은 Hard Delete인 경우
부모 엔티티가 Soft Delete, 자식 엔티티는 Hard Delete를 사용하는 경우 부모만 삭제한다.
자식 엔티티는 쿼리를 통해 명시적으로 삭제한다.
```kotlin
@Entity
@Table(name = "parent")
class Parent: AuditingEntity() {
    
    @OneToMany(mappedBy = "parent", cascade = [PERSIST, MERGE])
    protected val _children: MutableList<Child> = mutableListOf()
    val children: List<Child> get() = _children.toList()
    
    //부모만 삭제
}
```

#### 3. 둘 다 Hard Delete인 경우
CascadeType.ALL + orphanRemoval = true를 적용한다.
```kotlin
@Entity
@Table(name = "parent")
class Parent(
    child: Child
) : AuditingEntity() {

    @OneToMany(mappedBy="parent", cascade = [ALL], orphanRemoval = true)
    protected val _children: MutableList<Child> = mutableListOf()
    val children: List<Child> get() = _children.toList()

    fun removeChild(child: Child) {
      _children.remove(child)
    }
}
```
