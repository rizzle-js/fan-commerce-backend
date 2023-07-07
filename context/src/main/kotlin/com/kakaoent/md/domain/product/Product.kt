package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.domain.BaseEntity
import com.kakaoent.md.support.Language
import com.kakaoent.md.support.Period
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

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

    receiving: Receiving,
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
    protected val _options: MutableList<ProductOption> = mutableListOf()
    val options: List<ProductOption> get() = _options.toList()

    @Embedded
    var receiving: Receiving = receiving
        protected set

    fun addProductOption(productOption: ProductOption) {
        _options.add(productOption)
    }

    fun removeProductOption(productOption: ProductOption) {
        _options.remove(productOption)
    }

    fun getName(language: Language = Language.KOREAN): String {
        //todo: 다국어 지원
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

@Embeddable
class Receiving(
    @Enumerated(EnumType.STRING)
    @Column(name = "receiving_type", nullable = false, length = 20)
    val type: Type,

    receivingDates: List<ReceivingDate> = emptyList(),
) {

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "receiving_id")
    protected val _receivingDates: MutableList<ReceivingDate> = receivingDates.toMutableList()
    val receivingDates: List<ReceivingDate> get() = _receivingDates.toList()

    enum class Type(val code: Int, val value: String) {
        PICK_UP(1, "현장수령"),
        DELIVERY(2, "배송"),
    }
}

@Entity
@Table(name = "receiving_date")
class ReceivingDate(
    val date: LocalDate
): BaseEntity()
