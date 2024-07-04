package com.kakaoent.fan.infra.adapter.persistence

import com.kakaoent.fan.application.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

internal interface OrderRepository {
    fun save(order: Order): Order
    fun findById(id: Long): Order?
}

internal interface OrderJpaRepository : JpaRepository<Order, Long>

@Repository
internal class OrderRepositoryImpl(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepository {
    override fun save(order: Order): Order {
        return orderJpaRepository.save(order)
    }

    override fun findById(id: Long): Order? {
        return orderJpaRepository.findByIdOrNull(id)
    }
}
