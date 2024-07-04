package com.kakaoent.fan.infra.adapter

import com.kakaoent.fan.application.domain.Order
import com.kakaoent.fan.infra.adapter.client.ProductClient
import com.kakaoent.fan.infra.adapter.persistence.OrderRepository
import com.kakaoent.fan.port.outbound.OrderOut
import com.kakaoent.fan.port.outbound.OrderProductOut
import com.kakaoent.fan.port.outbound.OrderProductOutResult
import org.springframework.stereotype.Component

@Component
internal class OrderAdapter(
    private val orderRepository: OrderRepository,
    private val productClient: ProductClient
) : OrderOut, OrderProductOut {

    override fun save(order: Order): Order {
        return orderRepository.save(order)
    }

    override fun get(id: Long): Order {
        return orderRepository.findById(id) ?: throw NoSuchElementException("Order not found")
    }

    override fun getProduct(id: Long): OrderProductOutResult {
        return productClient.getProduct(id).toResult()
    }
}
