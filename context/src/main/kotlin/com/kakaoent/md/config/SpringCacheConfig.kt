package com.kakaoent.md.config

import org.redisson.api.RedissonClient
import org.redisson.spring.cache.CacheConfig
import org.redisson.spring.cache.RedissonSpringCacheManager
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class SpringCacheConfig {

    @Bean
    fun cacheManager(redissonClient: RedissonClient): CacheManager {

        val config = CacheConfig().apply {
            ttl = 60 * 60 * 24 * 7
            maxIdleTime = 60 * 60 * 24 * 7
        }

        return RedissonSpringCacheManager(redissonClient, mapOf(CODE_CATEGORIES_CACHE to config))
    }
}

const val CODE_CATEGORIES_CACHE = "codeCategories"
