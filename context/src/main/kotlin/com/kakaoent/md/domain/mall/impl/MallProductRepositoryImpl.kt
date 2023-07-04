package com.kakaoent.md.domain.mall.impl

import com.kakaoent.md.domain.mall.MallProduct
import com.kakaoent.md.domain.mall.MallProductRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

internal interface MallProductCrudRepository: CrudRepository<MallProduct, Long> {
    fun save(mallProduct: MallProduct): MallProduct
}

@Repository
internal class MallProductRepositoryImpl(
    private val repository: MallProductCrudRepository,
): MallProductRepository {
    override fun save(mallProduct: MallProduct): MallProduct = repository.save(mallProduct)

}
