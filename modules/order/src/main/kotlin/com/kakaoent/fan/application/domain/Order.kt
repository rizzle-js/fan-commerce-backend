package com.kakaoent.fan.application.domain

import com.kakaoent.fan.core.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    id: Long,
    buyerId: Long,
    productId: Long,
    quantity: Int,
    amount: Int
) : BaseEntity<Long>(id) {

    @Column(name = "buyer_id")
    var buyerId: Long = buyerId
        protected set

    @Column(name = "product_id")
    var productId: Long = productId
        protected set

    @Column(name = "quantity")
    var quantity: Int = quantity
        protected set

    @Column(name = "amount")
    var amount: Int = amount
        protected set
}
