package com.kakaoent.fan.port.inbound

import com.kakaoent.fan.application.domain.Product
import com.kakaoent.fan.application.domain.SaleStatus

interface GetProductIn {
    fun get(id: Long): GetProductInResult
}

data class GetProductInResult(
    val id: Long,
    val name: String,
    val saleStatus: SaleStatus,
    val price: Int
) {
    companion object {
        fun from(product: Product): GetProductInResult = with(product) {
            return GetProductInResult(
                id = id,
                name = name,
                saleStatus = saleStatus,
                price = price
            )
        }
    }
}
