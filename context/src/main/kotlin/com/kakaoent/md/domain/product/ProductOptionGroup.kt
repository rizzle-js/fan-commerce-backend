package com.kakaoent.md.domain.product

import com.kakaoent.md.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_option_group")
class ProductOptionGroup(
    name: String,
    productOptions: List<ProductOption> = emptyList(),
): BaseEntity() {

    @Column(name = "name", nullable = false, length = 100)
    var name: String = name
        protected set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "product_option_group_id")
    protected val _options: MutableList<ProductOption> = productOptions.toMutableList()
    val productOptions: List<ProductOption> get() = _options.toList()

    fun addProductOption(productOption: ProductOption) {
        _options.add(productOption)
    }

    fun removeProductOption(productOption: ProductOption) {
        _options.remove(productOption)
    }

}

@Entity
@Table(name = "product_option")
class ProductOption(

    @Column(name = "value", nullable = false, length = 100)
    val value: String,

    priority: Int = 0,
) : BaseEntity() {

    @Column(name = "priority", nullable = false)
    var priority: Int = priority
        protected set
}
