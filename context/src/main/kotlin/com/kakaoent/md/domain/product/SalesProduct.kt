package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "sales_product")
class SalesProduct(
    @Column(name = "sales_product_id", nullable = false, unique = true, length = 50)
    val salesProductId: String,

    @Column(name = "product_id", nullable = false, length = 50)
    val productId: String,

    quantity: Long,

    salesStatus: SalesStatus = SalesStatus.ON_SALE,
) : AuditingEntity() {

    @ManyToMany
    @JoinTable(
        name = "sales_product_option",
        joinColumns = [JoinColumn(name = "sales_product_id")],
        inverseJoinColumns = [JoinColumn(name = "product_option_id")]
    )
    protected var _salesProductOptions: MutableList<ProductOption> = mutableListOf()
    val salesProductOptions: List<ProductOption> get() = _salesProductOptions.toList()

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_status", nullable = false, length = 20)
    var salesStatus: SalesStatus = salesStatus
        protected set

    fun addSalesProductOption(productOption: ProductOption) {
        _salesProductOptions.add(productOption)
    }

    fun soldOut() {
        salesStatus = SalesStatus.SOLD_OUT
    }

    fun stopSale() {
        salesStatus = SalesStatus.STOP_SALE
    }

    fun resumeSale() {
        salesStatus = SalesStatus.ON_SALE
    }

    enum class SalesStatus(val value: String) {
        ON_SALE("판매 중"),
        SOLD_OUT("품절"),
        STOP_SALE("판매 중지"),
    }
}

fun List<SalesProduct>.fromOption(productOption: ProductOption): SalesProduct? {
    return find { productOption in it.salesProductOptions }
}
