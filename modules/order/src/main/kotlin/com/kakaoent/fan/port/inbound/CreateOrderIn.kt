package com.kakaoent.fan.port.inbound

interface CreateOrderIn {
    fun create(param: CreateOrderInParam): Long
}

data class CreateOrderInParam(
    val userId: Long,
    val productId: Long,
    val quantity: Int,
    val amount: Int
)
