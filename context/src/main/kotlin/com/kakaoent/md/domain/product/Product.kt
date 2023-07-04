package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.support.Period
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "product")
class Product(
    @Column(name = "product_id", nullable = false, unique = true)
    val productId: String,

    price: Long,

    status: Status,

    salesPeriod: Period,

    displayedAt: Instant
): AuditingEntity() {

    @Column(name = "price", nullable = false)
    var price: Long = price
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    var status: Status = status
        protected set

    @Column(name = "displayed_at", nullable = false)
    var displayedAt: Instant = displayedAt
        protected set

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "startedAt", column = Column(name = "sales_started_at", nullable = false)),
        AttributeOverride(name = "endedAt", column = Column(name = "sales_ended_at", nullable = false))
    )
    var salesPeriod: Period = salesPeriod
        protected set

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

    enum class DeliveryType(val value: String) {
        PICK_UP("현장수령"),
        DELIVERY("배송"),
    }
}
