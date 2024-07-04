package com.kakaoent.fan.application.domain

import com.kakaoent.fan.core.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class Product(
    id: Long,
    name: String,
    saleStatus: SaleStatus,
    price: Int
) : BaseEntity<Long>(id) {

    @Column(name = "name")
    var name: String = name
        protected set

    @Column(name = "sale_status")
    var saleStatus: SaleStatus = saleStatus
        protected set

    @Column(name = "price")
    var price: Int = price
        protected set

    fun sale() {
        saleStatus = SaleStatus.ON_SALE
    }

    fun endSale() {
        saleStatus = SaleStatus.END_SALE
    }
}

enum class SaleStatus {
    ON_SALE, END_SALE
}
