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

    purchaseLimits: Int?,

    status: Status,

    salesPeriod: Period,

    displaying: Displaying,

    receiving: Receiving,

    badge: Badge,

    tax: Tax,

    production: Production,

    salesInformationDisclosure: SalesInformationDisclosure,

    certification: Certification,
) : AuditingEntity() {

    @Column(name = "name", nullable = false, length = 100)
    var name: String = name
        protected set

    @Column(name = "description", nullable = false, length = 255)
    var description: String = description
        protected set

    @Column(name = "price", nullable = false)
    var price: Long = price
        protected set

    @Column(name = "purchase_limits", nullable = true)
    var purchaseLimits: Int? = purchaseLimits
        protected set

    @Column(name = "fee_rate", nullable = false)
    var feeRate: BigDecimal = feeRate
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    var status: Status = status
        protected set

    @Embedded
    var receiving: Receiving = receiving
        protected set

    @Embedded
    var badge: Badge = badge
        protected set

    @Embedded
    var displaying: Displaying = displaying
        protected set

    @Embedded
    var tax: Tax = tax
        protected set

    @Embedded
    var production: Production = production
        protected set

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "startedAt", column = Column(name = "sales_started_at", nullable = false)),
        AttributeOverride(name = "endedAt", column = Column(name = "sales_ended_at", nullable = false))
    )
    var salesPeriod: Period = salesPeriod
        protected set

    @Embedded
    var salesInformationDisclosure: SalesInformationDisclosure = salesInformationDisclosure
        protected set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_id")
    protected val _productOptionGroups: MutableList<ProductOptionGroup> = mutableListOf()
    val productOptionGroups: List<ProductOptionGroup> get() = _productOptionGroups.sortedBy { it.ordering }


    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_id")
    protected val _productImages: MutableList<ProductImage> = mutableListOf()

    val thumbnailImage: ProductImage? get() = _productImages.find { it.type == ProductImage.Type.THUMBNAIL }

    val careLabelImage: ProductImage? get() = _productImages.find { it.type == ProductImage.Type.CARE_LABEL }

    val productImages: List<ProductImage> get() = _productImages.filter { it.type == ProductImage.Type.PRODUCT }

    val detailImages: List<ProductImage> get() = _productImages.filter { it.type == ProductImage.Type.DETAIL }

    @Embedded
    var certification: Certification = certification
        protected set

    fun getName(language: Language = Language.KOREAN): String {
        //todo: 다국어 지원
        return this.name
    }

    fun displayNew(time: Instant): Boolean {
        if (badge.applyNew.not()) return false
        val displayedAt = displaying.displayedAt ?: return false
        val until = displayedAt.plusSeconds(60 * 60 * 24 * 7)
        return time.isBefore(until)
    }

    fun addProductOptionGroup(productOptionGroup: ProductOptionGroup) {
        _productOptionGroups.add(productOptionGroup)
    }

    fun removeProductOptionGroup(productOptionGroup: ProductOptionGroup) {
        _productOptionGroups.remove(productOptionGroup)
    }

    fun addProductImage(productImage: ProductImage) {
        _productImages.add(productImage)
    }

    fun removeProductImage(productImage: ProductImage) {
        _productImages.remove(productImage)
    }

    enum class Status(val value: String) {
        UPCOMING("판매 예정"),
        ON_SALE("판매 가능"),
        SOLD_OUT("품절"),
        STOP_SALE("판매 중지"),
        CLOSE_SALE("판매 완료")
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
    @JoinColumn(name = "product_id")
    protected val _receivingDates: MutableList<ReceivingDate> = receivingDates.toMutableList()
    val receivingDates: List<ReceivingDate> get() = _receivingDates.sortedBy { it.date }

    enum class Type(val code: Int, val value: String) {
        PICK_UP(1, "현장수령"),
        DELIVERY(2, "배송"),
    }
}

@Entity
@Table(name = "receiving_date")
class ReceivingDate(
    @Column(name = "date", nullable = false)
    val date: LocalDate
) : BaseEntity()

@Entity
@Table(name = "product_image")
class ProductImage(
    @Column(name = "image_url", nullable = false, length = 255)
    val imageUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    val type: Type,

    @Column(name = "ordering", nullable = true)
    val ordering: Int?,
) : BaseEntity() {

    enum class Type(val value: String) {
        THUMBNAIL("썸네일"),
        PRODUCT("상품"),
        DETAIL("상세"),
        CARE_LABEL("케어라벨"),
        ETC("기타"),
    }
}
