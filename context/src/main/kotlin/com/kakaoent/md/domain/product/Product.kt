package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.support.Language
import com.kakaoent.md.support.Period
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "product")
class Product(
    @Column(name = "product_id", nullable = false, unique = true, length = 50)
    val productId: String,

    name: String,

    description: String,

    price: Long,

    feeRate: BigDecimal = BigDecimal.ZERO,

    thumbnailImageUrl: String,

    status: Status,

    salesPeriod: Period,

    displayedAt: Instant?,
) : AuditingEntity() {

    @Column(name = "name", nullable = false, length = 100)
    protected var name: String = name

    @Column(name = "description", nullable = false, length = 255)
    protected var description: String = description

    @Column(name = "price", nullable = false)
    var price: Long = price
        protected set

    @Column(name = "fee_rate", nullable = false)
    var feeRate: BigDecimal = feeRate
        protected set

    @Column(name = "thumbnail_image_url", nullable = false, length = 255)
    var thumbnailImageUrl: String = thumbnailImageUrl
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    var status: Status = status
        protected set

    @Column(name = "displayed_at", nullable = false)
    var displayedAt: Instant? = displayedAt
        protected set

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "startedAt", column = Column(name = "sales_started_at", nullable = false)),
        AttributeOverride(name = "endedAt", column = Column(name = "sales_ended_at", nullable = false))
    )
    var salesPeriod: Period = salesPeriod
        protected set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_id")
    protected val _optionGroups: MutableList<ProductOptionGroup> = mutableListOf()
    val optionGroups: List<ProductOptionGroup> get() = _optionGroups.toList()

    fun addProductOptionGroup(productOptionGroup: ProductOptionGroup) {
        _optionGroups.add(productOptionGroup)
    }

    fun removeProductOptionGroup(productOptionGroup: ProductOptionGroup) {
        _optionGroups.remove(productOptionGroup)
    }

    fun getName(language: Language = Language.KOREAN): String {
        return this.name
    }

    enum class Status(val value: String) {
        UPCOMING("판매 예정"),
        ON_SALE("판매 가능"),
        SOLD_OUT("품절"),
        STOP_SALE("판매 중지"),
        CLOSE_SALE("판매 완료")
    }

    enum class DisplayType(val value: String) {
        DISPLAY("전시"),
        HIDE("미전시")
    }
}
