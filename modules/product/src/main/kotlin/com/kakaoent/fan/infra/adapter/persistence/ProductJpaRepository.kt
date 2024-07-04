package com.kakaoent.fan.infra.adapter.persistence

import com.kakaoent.fan.application.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<Product, Long>
