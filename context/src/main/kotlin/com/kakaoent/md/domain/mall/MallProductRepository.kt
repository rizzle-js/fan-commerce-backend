package com.kakaoent.md.domain.mall

interface MallProductRepository {

    fun save(mallProduct: MallProduct): MallProduct

}
