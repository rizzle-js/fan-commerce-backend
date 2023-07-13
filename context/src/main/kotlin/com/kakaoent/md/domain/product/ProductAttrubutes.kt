package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.AuditingEntity
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant

@Embeddable
data class Displaying(
    @Enumerated(EnumType.STRING)
    @Column(name = "display_type", nullable = false, length = 20)
    val displayType: DisplayType,

    @Column(name = "displayed_at")
    val displayedAt: Instant?,
) {

    enum class DisplayType(val value: String) {
        DISPLAY("전시"),
        HIDE("미전시")
    }

    companion object {
        fun display(displayedAt: Instant): Displaying = Displaying(displayType = DisplayType.DISPLAY, displayedAt = displayedAt)
        fun hide(): Displaying = Displaying(displayType = DisplayType.HIDE, displayedAt = null)
    }
}

@Embeddable
data class Badge(
    @Column(name = "apply_new", nullable = false)
    val applyNew: Boolean
)

@Embeddable
data class Tax(
    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", nullable = false, length = 20)
    val taxType: Type,

    @Column(name = "tax_rate", nullable = true)
    val taxRate: BigDecimal?,
) {

    enum class Type(val value: String) {
        TAX("과세"),
        TAX_FREE("비과세"),
    }

    companion object {
        fun tax(rate: BigDecimal): Tax = Tax(taxType = Type.TAX, taxRate = rate)
        fun taxFree(): Tax = Tax(taxType = Type.TAX_FREE, taxRate = null)
    }
}

@Embeddable
data class Production(
    @Enumerated(EnumType.STRING)
    @Column(name = "production_type", nullable = false, length = 20)
    val productionType: Type,

    @Column(name = "production_duration", nullable = true)
    val productionDuration: Int?
) {

    enum class Type(val value: String) {
        MADE_TO_ORDER("주문제작"),
        ALWAYS_ON("상시상품"),
    }

    companion object {
        fun alwaysOn(): Production = Production(productionType = Type.ALWAYS_ON, productionDuration = null)
        fun madeToOrder(duration: Int): Production = Production(productionType = Type.MADE_TO_ORDER, productionDuration = duration)
    }
}

@Embeddable
data class SalesInformationDisclosure(
    @Column(name = "seller", nullable = false, length = 1500)
    val seller: String,

    @Column(name = "selling_information", nullable = false, length = 1500)
    val sellingInformation: String,

    @Column(name = "product_information", nullable = false, length = 1500)
    val productInformation: String,

    @Column(name = "trade_term", nullable = false, length = 1500)
    val tradeTerm: String,

    @Column(name = "delivery_term", nullable = false, length = 1500)
    val deliveryTerm: String,

    @Column(name = "caution", nullable = false, length = 1500)
    val caution: String,
) {
    companion object {

        fun empty(): SalesInformationDisclosure = SalesInformationDisclosure(
            seller = "",
            sellingInformation = "",
            productInformation = "",
            tradeTerm = "",
            deliveryTerm = "",
            caution = "",
        )
    }
}

@Embeddable
data class Certification(
    @Enumerated(EnumType.STRING)
    @Column(name = "certification_type", nullable = false, length = 20)
    val certificationType: Type,

    @Column(name = "certification_number", nullable = true, length = 255)
    val certificationNumber: String?,

    @Column(name = "expose_kc_mark", nullable = true)
    val exposeKCMark: Boolean?,

    @Enumerated(EnumType.STRING)
    @Column(name = "docs_type", nullable = true, length = 20)
    val docsType: DocsType?,

    @Column(name = "document_path", nullable = true, length = 255)
    val documentPath: String?,
) {

    enum class Type(val description: String) {
        NONE("비대상"),
        NON_EXEMPT("대상(면제 불가)"),
        EXEMPT("대상(면제 가능)"),
    }

    enum class DocsType(val description: String) {
        INSPECTION_REPORTS("제품검사결과서"),
        SUPPLIER_CONFORMITY_CERTIFICATES("공급자적합성확인서"),
        PRODUCT_MANUALS("제품설명서")
    }

    companion object {
        fun none(): Certification = Certification(certificationType = Type.NONE, certificationNumber = null, exposeKCMark = null, documentPath = null, docsType = null)
    }
}
