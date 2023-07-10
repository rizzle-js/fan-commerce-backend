package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_option_group")
class ProductOptionGroup(
    name: String,
    ordering: Int = 0,
) : BaseEntity() {

    @Column(name = "name", nullable = false, length = 100)
    var name: String = name
        protected set

    @Column(name = "ordering", nullable = false)
    var ordering: Int = ordering
        protected set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_option_group_id")
    protected var _productOptions: MutableList<ProductOption> = mutableListOf()
    val productOptions: List<ProductOption> get() = _productOptions.sortedBy { it.ordering }

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
    ordering: Int = 0,
) : BaseEntity() {

    @Column(name = "value", nullable = false, length = 100)
    var value: String = value
        protected set

    @Column(name = "ordering", nullable = false)
    var ordering: Int = ordering
        protected set
}
