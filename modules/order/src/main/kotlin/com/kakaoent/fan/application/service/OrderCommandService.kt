package com.kakaoent.fan.application.service

import com.kakaoent.fan.application.domain.Order
import com.kakaoent.fan.application.event.OrderCreatedEvent
import com.kakaoent.fan.port.inbound.CreateOrderIn
import com.kakaoent.fan.port.inbound.CreateOrderInParam
import com.kakaoent.fan.port.outbound.OrderOut
import com.kakaoent.fan.port.outbound.OrderProductOut
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class OrderCommandService(
    private val orderOut: OrderOut,
    private val orderProductOut: OrderProductOut,
    private val eventPublisher: ApplicationEventPublisher
) : CreateOrderIn {

    @Transactional
    override fun create(param: CreateOrderInParam): Long {
        val product = orderProductOut.getProduct(param.productId)

        require(product.price * param.quantity == param.amount) {
            "Invalid amount"
        }

        val order = Order(
            id = Random.nextLong(),
            buyerId = param.userId,
            productId = param.productId,
            quantity = param.quantity,
            amount = param.amount
        )

        eventPublisher.publishEvent(
            OrderCreatedEvent(
                id = Random.nextLong(),
                orderId = order.id
            )
        )

        return orderOut.save(order).id
    }
}
