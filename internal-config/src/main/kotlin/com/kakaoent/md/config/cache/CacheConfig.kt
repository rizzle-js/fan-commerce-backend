package com.kakaoent.md.config.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfig {

    //todo Cache Manager Redis로 변경
    @Bean
    fun cacheManager(): CacheManager {

        return SimpleCacheManager().apply {
            setCaches(
                listOf(
                    ConcurrentMapCache("codeCategories")
                )
            )
        }
    }
}
