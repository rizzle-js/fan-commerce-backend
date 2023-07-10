package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_option_group")
class ProductOptionGroup(
    name: String,
    priority: Int = 0,
) : BaseEntity() {

    @Column(name = "name", nullable = false, length = 100)
    var name: String = name
        protected set

    @Column(name = "priority", nullable = false)
    var priority: Int = priority
        protected set

    @OneToMany(mappedBy = "productOptionGroup", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected var _productOptions: MutableList<ProductOption> = mutableListOf()
    val productOptions: List<ProductOption> get() = _productOptions.toList()

    fun addProductOption(productOption: ProductOption) {
        _productOptions.add(productOption)
    }

    fun removeProductOption(productOption: ProductOption) {
        _productOptions.remove(productOption)
    }
}

@Entity
@Table(name = "product_option")
class ProductOption(
    value: String,
    priority: Int = 0,

    @ManyToOne
    @JoinColumn(name = "product_option_group_id", nullable = false)
    val productOptionGroup: ProductOptionGroup,
) : BaseEntity() {

    @Column(name = "value", nullable = false, length = 100)
    var value: String = value
        protected set

    @Column(name = "priority", nullable = false)
    var priority: Int = priority
        protected set
}
