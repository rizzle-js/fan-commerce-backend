package com.kakaoent.fan.application.service

import com.kakaoent.fan.port.inbound.EndSaleProductIn
import com.kakaoent.fan.port.outbound.ProductOut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class ProductCommandService(
    private val productOut: ProductOut
) : EndSaleProductIn {

    @Transactional
    override fun endSale(productId: Long) {
        val product = productOut.get(productId)
        product.endSale()
    }
}
