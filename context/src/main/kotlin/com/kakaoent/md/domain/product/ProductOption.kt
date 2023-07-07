package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_option")
class ProductOption(
    value: String,
    priority: Int = 0,
) : BaseEntity() {

    @Column(name = "value", nullable = false, length = 100)
    var value: String = value
        protected set

    @Column(name = "priority", nullable = false)
    var priority: Int = priority
        protected set
}
