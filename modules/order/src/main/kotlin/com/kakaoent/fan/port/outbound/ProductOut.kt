package com.kakaoent.fan.port.outbound

interface OrderProductOut {
    fun getProduct(id: Long): OrderProductOutResult
}

data class OrderProductOutResult(
    val id: Long,
    val name: String,
    val price: Int
)
