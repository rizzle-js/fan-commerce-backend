package com.kakaoent.md.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import redis.embedded.RedisServerBuilder
@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,
    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config().apply {
            useSingleServer().address = "redis://$host:$port"
        }
        return Redisson.create(config)
    }
}

@Configuration
internal class LocalRedisConfig(
    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun redisServer() {
        println("레디스")
        redisServer = RedisServerBuilder()
            .port(port)
            .setting("maxmemory 128M")
            .build()

        try {
            redisServer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }
}
