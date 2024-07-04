package com.kakaoent.fan.application.service

import com.kakaoent.fan.port.inbound.GetProductIn
import com.kakaoent.fan.port.inbound.GetProductInResult
import com.kakaoent.fan.port.outbound.ProductOut
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class ProductQueryService(
    private val productOut: ProductOut
) : GetProductIn {

    @Transactional(readOnly = true)
    override fun get(id: Long): GetProductInResult {
        val product = productOut.get(id)
        return GetProductInResult.from(product)
    }
}
