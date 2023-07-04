package com.kakaoent.md.domain.mall

import com.kakaoent.md.domain.AuditingEntity
import com.kakaoent.md.domain.DOMAIN_ID_LENGTH
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "mall_product")
class MallProduct(
    @Column(name = "mall_id", nullable = false, updatable = false, length = DOMAIN_ID_LENGTH)
    val mallId: String,

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false, updatable = false)
//    val product: Product
    @Column(name = "product_id", nullable = false, updatable = false, length = DOMAIN_ID_LENGTH)
    val productId: String
): AuditingEntity()
