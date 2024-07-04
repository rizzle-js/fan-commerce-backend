package com.kakaoent.fan.api.event.something

import com.kakaoent.fan.application.event.OrderCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Consumer {

    @EventListener
    fun onEvent(event: OrderCreatedEvent) {
        // do something
    }
}
