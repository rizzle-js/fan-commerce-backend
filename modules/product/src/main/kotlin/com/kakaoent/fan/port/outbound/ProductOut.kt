package com.kakaoent.fan.port.outbound

import com.kakaoent.fan.application.domain.Product

internal interface ProductOut {
    fun save(product: Product): Product
    fun get(id: Long): Product
}
