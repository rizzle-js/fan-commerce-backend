package com.kakaoent.fan.infra.adapter.client

import com.kakaoent.fan.port.inbound.GetProductIn
import com.kakaoent.fan.port.outbound.OrderProductOutResult
import org.springframework.stereotype.Component

internal interface ProductClient {
    fun getProduct(productId: Long): ProductResponse
}

internal data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int
) {
    fun toResult(): OrderProductOutResult = OrderProductOutResult(
        id = id,
        name = name,
        price = price
    )
}

@Component
internal class ProductMemoryClient(
    private val getProductIn: GetProductIn
) : ProductClient {
    override fun getProduct(productId: Long): ProductResponse = with(getProductIn.get(productId)) {
        ProductResponse(
            id = id,
            name = name,
            price = price
        )
    }
}
