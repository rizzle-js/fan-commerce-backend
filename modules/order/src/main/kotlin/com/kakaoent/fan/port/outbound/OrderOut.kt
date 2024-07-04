package com.kakaoent.fan.port.outbound

import com.kakaoent.fan.application.domain.Order

interface OrderOut {
    fun save(order: Order): Order
    fun get(id: Long): Order
}
